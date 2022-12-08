package com.example.order_system.service.serviceImpl;

import com.example.order_system.dto.CustomerDto;
import com.example.order_system.dto.response.ResponseCustomerLastOrders;
import com.example.order_system.dto.response.ResponseCustomerNumberOfProductsInYear;
import com.example.order_system.entity.CustomerEntity;
import com.example.order_system.entity.OrderEntity;
import com.example.order_system.repository.CustomerRepo;
import com.example.order_system.repository.OrderRepo;
import com.example.order_system.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;


    @Override
    public ResponseEntity<List<CustomerDto>> getCustomersWithoutOrders() {
        List<OrderEntity> orderEntity = orderRepo.findAll();
        List<CustomerEntity> customerEntity = customerRepo.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();

        orderEntity.forEach(orderEntity1 -> {
            if (orderEntity1.getDate().getYear() == 2016){
             customerEntity.removeIf(c -> c.getId().equals(orderEntity1.getCustomer().getId()));
            }
        });

        customerEntity.forEach(customer -> {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(customer, customerDto);
            customerDtos.add(customerDto);
        });
        return ResponseEntity.ok(customerDtos);
    }

    @Override
    public ResponseEntity<List<ResponseCustomerLastOrders>> getCustomersLastOrders() {
        List<CustomerEntity> customerEntity = customerRepo.findAll();
        List<ResponseCustomerLastOrders> responseCustomerLastOrders = new ArrayList<>();
        customerEntity.forEach(customer -> {
            Optional<OrderEntity> orderEntity1 = orderRepo.findTopByCustomerIdOrderByDateDesc(customer.getId());

            if (orderEntity1.isPresent()){
                OrderEntity orderEntity = orderEntity1.get();
                ResponseCustomerLastOrders responseCustomerLastOrders1 = new ResponseCustomerLastOrders();
                responseCustomerLastOrders1.setCustomerId(orderEntity.getCustomer().getId());
                responseCustomerLastOrders1.setCustomerName(customer.getName());
                responseCustomerLastOrders1.setLastOrderDate(orderEntity.getDate());
                responseCustomerLastOrders.add(responseCustomerLastOrders1);
            }
        });
        return ResponseEntity.ok(responseCustomerLastOrders);
    }

    @Override
    public List<ResponseCustomerNumberOfProductsInYear> getTotalNumberOfProducts() {
        List<ResponseCustomerNumberOfProductsInYear> results = new ArrayList<>();
        List<CustomerEntity> customerEntityList = customerRepo.findAll();
        Map<String, Integer> map = new HashMap<>();
        customerEntityList.forEach(entity ->{
            // Order List
            List<OrderEntity> orderEntityList=orderRepo.findAllByCustomerId(entity.getId());
            orderEntityList.forEach(orderEntity -> {
                if (orderEntity.getDate().getYear() == 2016) {
                    // Customer List byId
                    List<CustomerEntity> customerEntities =
                            customerRepo.findAllById(Collections.singleton(orderEntity.getCustomerId()));
                    customerEntities.forEach(customerEntity -> {
                        if (map.containsKey(customerEntity.getCountry())) {
                            map.replace(customerEntity.getCountry(), map.get(customerEntity.getCountry()) + 1);
                        } else
                            map.put(customerEntity.getCountry(), 1);
                    });
                }
            });
        });

        for (Map.Entry<String, Integer> entry: map.entrySet()){
            ResponseCustomerNumberOfProductsInYear result=new ResponseCustomerNumberOfProductsInYear();
            result.setCountry(entry.getKey());
            result.setTotalNumberOfOrders(entry.getValue());
            results.add(result);
        }
        return results;
    }
}