package com.example.order_system.service.serviceImpl;

import com.example.order_system.dto.PaymentDto;
import com.example.order_system.dto.request.RequestPayment;
import com.example.order_system.dto.response.ResponsePayment;
import com.example.order_system.dto.response.ResponsePaymentOverpaid;
import com.example.order_system.entity.InvoiceEntity;
import com.example.order_system.entity.PaymentEntity;
import com.example.order_system.repository.InvoiceRepo;
import com.example.order_system.repository.PaymentRepo;
import com.example.order_system.service.PaymentService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PaymentInpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Override
    public ResponsePayment createNewUser(Long invoiceId) {
        ResponsePayment responsePayment = new ResponsePayment();
        Optional<InvoiceEntity> invoiceEntity = invoiceRepo.findById(invoiceId);
        if (invoiceEntity.isEmpty()){
            responsePayment.setStatus("FAILED");
            return responsePayment;
        }

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setTime(new Timestamp(System.currentTimeMillis()));
        paymentEntity.setAmount(invoiceEntity.get().getAmount());
        paymentEntity.setInvoiceId(invoiceId);
        paymentEntity.setInvoice(invoiceEntity.get());
        paymentRepo.save(paymentEntity);

        responsePayment.setStatus("SUCCESS");
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(paymentEntity, paymentDto);
        responsePayment.setPayment(paymentDto);
        return responsePayment;
    }

    @Override
    public ResponseEntity<PaymentDto> getPaymentId(Long payment_details_id) {
        Optional<PaymentEntity> paymentEntity = paymentRepo.findById(payment_details_id);
        if (paymentEntity.isPresent()){
            PaymentEntity paymentEntity1 = paymentEntity.get();
            PaymentDto paymentDto = new PaymentDto();
            BeanUtils.copyProperties(paymentEntity1, paymentDto);
            return ResponseEntity.ok(paymentDto);
        }
        return null;
    }

    @Override
    public PaymentDto createPayment(RequestPayment dto) {
        PaymentDto paymentDto = new PaymentDto();
        Optional<InvoiceEntity> invoiceEntity = invoiceRepo.findById(dto.getInvoiceId());
        if (invoiceEntity.isPresent()){
            PaymentEntity paymentEntity = new PaymentEntity();
            paymentEntity.setTime(new Timestamp(System.currentTimeMillis()));
            paymentEntity.setAmount(dto.getAmount());
            paymentEntity.setInvoiceId(dto.getInvoiceId());
            paymentRepo.save(paymentEntity);
            BeanUtils.copyProperties(paymentEntity, paymentDto);
        }
        return paymentDto;
    }

    @Override
    public ResponseEntity<ResponsePaymentOverpaid> getPayOverpaid(Long invoiceId) {
        ResponsePaymentOverpaid payment = new ResponsePaymentOverpaid();
        Integer sum =0;

        Optional<InvoiceEntity> invoiceEntity = invoiceRepo.findById(invoiceId);
        if (invoiceEntity.isPresent()){
            List<PaymentEntity> paymentEntities = paymentRepo.findAllByInvoiceId(invoiceId);
            for (PaymentEntity paymentEntity : paymentEntities) {
                sum += paymentEntity.getAmount();
            }
            if (sum>invoiceEntity.get().getAmount()) {
                payment.setAmount(sum - invoiceEntity.get().getAmount());
                payment.setInvoiceId(invoiceId);
            }
        }
        return ResponseEntity.ok(payment);
    }

    @Override
    public List<ResponsePaymentOverpaid> getOverpaidInvoices() {
        List<InvoiceEntity> invoiceEntities = invoiceRepo.findAll();
        List<ResponsePaymentOverpaid> results = new ArrayList<>();

        invoiceEntities.forEach(entity ->{
            Integer sum =0;
            List<PaymentEntity> paymentEntities = paymentRepo.findAllByInvoiceId(entity.getId());
            for (PaymentEntity en : paymentEntities) {
                sum+=en.getAmount();
            }
            if (sum>entity.getAmount()){
                ResponsePaymentOverpaid responsePaymentOverpaid = new ResponsePaymentOverpaid();
                responsePaymentOverpaid.setInvoiceId(entity.getId());
                responsePaymentOverpaid.setAmount(sum-entity.getAmount());
                results.add(responsePaymentOverpaid);
            }

        });
        return results;
    }

}
