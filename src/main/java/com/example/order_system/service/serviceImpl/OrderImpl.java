package com.example.order_system.service.serviceImpl;

import com.example.order_system.dto.OrderDto;
import com.example.order_system.dto.request.CreateOrder;
import com.example.order_system.dto.response.OrderDetailsResponse;
import com.example.order_system.dto.response.ResponseOrder;
import com.example.order_system.dto.response.ResponseOrderWithoutInvoices;
import com.example.order_system.entity.*;
import com.example.order_system.repository.*;
import com.example.order_system.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class OrderImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private DetailRepo detailRepo;
    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public ResponseOrder createOrder(CreateOrder dto) {
        ResponseOrder responseOrder = new ResponseOrder();
        Optional<ProductEntity> productEntity=productRepo.findById(dto.getProductId());
        if (productEntity.isEmpty()){
            responseOrder.setStatus("FAILED");
            responseOrder.setInvoiceNumber(-1L);
            return responseOrder;
        }

        Optional<CustomerEntity> customerEntity=customerRepo.findById(dto.getCustomerId());
        if (customerEntity.isEmpty()){
            responseOrder.setStatus("FAILED");
            responseOrder.setInvoiceNumber(-1L);
            return responseOrder;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate(LocalDate.now());
        orderEntity.setCustomerId(dto.getCustomerId());
        orderEntity.setCustomer(customerEntity.get());
        orderRepo.saveAndFlush(orderEntity);

        DetailEntity detailEntity=new DetailEntity();
        detailEntity.setOrderId(orderEntity.getId());
        detailEntity.setProductId(dto.getProductId());
        detailEntity.setQuantity(dto.getQuantity());
        detailRepo.saveAndFlush(detailEntity);

        InvoiceEntity invoiceEntity=new InvoiceEntity();
        invoiceEntity.setOrderId(orderEntity.getId());
        invoiceEntity.setAmount(dto.getQuantity()*productEntity.get().getPrice());
        invoiceEntity.setIssued(LocalDate.now());
        invoiceEntity.setDue(LocalDate.now().plusDays(3));
        invoiceRepo.saveAndFlush(invoiceEntity);

        responseOrder.setStatus("SUCCESS");
        responseOrder.setInvoiceNumber(invoiceEntity.getId());
        return responseOrder;
    }



    @Override
    public OrderDetailsResponse getOrderId(Long orderId) {
        OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();

        Optional<OrderEntity> orderEntity = orderRepo.findById(orderId);
        if (orderEntity.isPresent()){
            OrderEntity orderEntity1 = orderEntity.get();
            orderDetailsResponse.setDate(orderEntity1.getDate());

            // Customer class
            Optional<CustomerEntity> customerEntity = customerRepo.findById(orderEntity1.getCustomerId());
            if (customerEntity.isPresent()){
                CustomerEntity customerEntity1 = customerEntity.get();

                orderDetailsResponse.setCustomerName(customerEntity1.getName());
                orderDetailsResponse.setCustomerCountry(customerEntity1.getCountry());
                orderDetailsResponse.setCustomerAddress(customerEntity1.getAddress());
                orderDetailsResponse.setCustomerPhone(customerEntity1.getPhone());
            }

            // Invoice class
            Optional<InvoiceEntity> invoiceEntity = invoiceRepo.findByOrderId(orderId);
            if (invoiceEntity.isPresent()){

                InvoiceEntity invoiceEntity1 = invoiceEntity.get();
                orderDetailsResponse.setAmount(invoiceEntity1.getAmount());
            }

            // Detail class
            Optional<DetailEntity> detailEntity = detailRepo.findByOrderId(orderId);
            if (detailEntity.isPresent()){
                // Product class
                Optional<ProductEntity> productEntity = productRepo.findById(detailEntity.get().getProductId());
                productEntity.ifPresent(entity -> orderDetailsResponse.setProductName(entity.getName()));
            }
        }
        return orderDetailsResponse;
    }

    @Override
    public ResponseEntity<List<OrderDto>> getOrdersWithoutDetails() {
        List<OrderEntity> orderEntity = orderRepo.findAll();
        List<DetailEntity>  detailEntities = detailRepo.findAll();
        List<OrderDto> orderDtos=new ArrayList<>();

        orderEntity.forEach(orderEntity1 -> {
            if (orderEntity1.getDate().isBefore(LocalDate.of(2016, 9, 6))){
                boolean b = false;
                for (DetailEntity detailEntity : detailEntities) {
                    if (detailEntity.getOrder().getId().equals(orderEntity1.getId())) {
                        b = true;
                        break;
                    }
                }
                if (!b){
                    OrderDto orderDto = new OrderDto();
                    BeanUtils.copyProperties(orderEntity1, orderDto);
                    orderDtos.add(orderDto);
                }
            }
        });

        return ResponseEntity.ok(orderDtos);
    }

    @Override
    public List<ResponseOrderWithoutInvoices> getOrdersWithoutInvoices() {
        List<ResponseOrderWithoutInvoices> results=new ArrayList<>();
        List<OrderEntity> orderEntityList=orderRepo.findAll();
        orderEntityList.forEach(entity ->{
            Optional<InvoiceEntity> invoiceEntity = invoiceRepo.findByOrderId(entity.getId());
            if (invoiceEntity.isEmpty()){
                int i = 0;
                List<DetailEntity> detailEntities = detailRepo.findAllByOrderId(entity.getId());
                for (DetailEntity detailEntity : detailEntities) {
                    Optional<ProductEntity> productEntity = productRepo.findById(detailEntity.getProductId());
                    if (productEntity.isPresent()) {
                        i += productEntity.get().getPrice() * detailEntity.getQuantity();
                    }
                }

                ResponseOrderWithoutInvoices responseOrderWithoutInvoices = new ResponseOrderWithoutInvoices();
                responseOrderWithoutInvoices.setOrderId(entity.getId());
                responseOrderWithoutInvoices.setOrderDate(entity.getDate());
                responseOrderWithoutInvoices.setTotalPrice(i);
                results.add(responseOrderWithoutInvoices);
           }
        });
        return results;
    }
}
