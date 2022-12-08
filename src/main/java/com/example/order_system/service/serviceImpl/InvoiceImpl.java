package com.example.order_system.service.serviceImpl;

import com.example.order_system.dto.InvoiceDto;
import com.example.order_system.dto.response.ResponceInvoice;
import com.example.order_system.entity.InvoiceEntity;
import com.example.order_system.entity.OrderEntity;
import com.example.order_system.repository.InvoiceRepo;
import com.example.order_system.repository.OrderRepo;
import com.example.order_system.service.InvoiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public ResponseEntity<List<InvoiceDto>> getExpiredUsers() {
        List<InvoiceEntity> invoiceEntities = invoiceRepo.findAll();
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        invoiceEntities.forEach(invoiceEntity -> {
            if (invoiceEntity.getDue().isBefore(LocalDate.now())){
                InvoiceDto invoiceDto = new InvoiceDto();
                BeanUtils.copyProperties(invoiceEntity, invoiceDto);
                invoiceDtos.add(invoiceDto);
            }
        });

        return ResponseEntity.ok(invoiceDtos);
    }

    @Override
    public List<ResponceInvoice> getWrongDateInvoices() {
        List<ResponceInvoice> responceInvoices = new ArrayList<>();
        List<InvoiceEntity> invoiceEntities = invoiceRepo.findAll();

        invoiceEntities.forEach(invoiceEntity -> {
            Optional<OrderEntity> orderEntity = orderRepo.findById(invoiceEntity.getOrder().getId());
            if (orderEntity.isPresent()){
                if (orderEntity.get().getDate().isAfter(invoiceEntity.getIssued())){
                    ResponceInvoice responceInvoice = new ResponceInvoice();
                    responceInvoice.setInvoiceId(invoiceEntity.getId());
                    responceInvoice.setOrderId(orderEntity.get().getId());
                    responceInvoice.setInvoiceIssued(invoiceEntity.getIssued());
                    responceInvoice.setOrderDate(orderEntity.get().getDate());
                    responceInvoices.add(responceInvoice);
                }
            }
        } );

        return responceInvoices;
    }
}
