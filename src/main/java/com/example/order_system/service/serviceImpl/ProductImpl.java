package com.example.order_system.service.serviceImpl;

import com.example.order_system.dto.ProductDto;
import com.example.order_system.dto.response.ResponseBulkProducts;
import com.example.order_system.dto.response.ResponsePaymentHightDemandProducts;
import com.example.order_system.entity.CategoryEntity;
import com.example.order_system.entity.DetailEntity;
import com.example.order_system.entity.ProductEntity;
import com.example.order_system.repository.DetailRepo;
import com.example.order_system.repository.ProductRepo;
import com.example.order_system.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DetailRepo detailRepo;


    @Override
    public ResponseEntity<List<ProductDto>> getUsers() {
        List<ProductEntity> productEntities=productRepo.findAll();
        List<ProductDto> productDtos=new ArrayList<>();
        productEntities.forEach(productEntity -> {
            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(productEntity, productDto);
            productDtos.add(productDto);
        });
        return ResponseEntity.ok(productDtos);
    }

    @Override
    public ResponseEntity<ProductDto> getProductId(Long productId) {
        Optional<ProductEntity> productEntity=productRepo.findById(productId);
        if (productEntity.isPresent()){
            ProductEntity entity = productEntity.get();
            ProductDto  productDto = new ProductDto();
            BeanUtils.copyProperties(entity, productDto);
            return ResponseEntity.ok(productDto);
        }
        return null;
    }

    @Override
    public List<ResponsePaymentHightDemandProducts> getHightDemandProducts() {
        List<ResponsePaymentHightDemandProducts> responsePaymentHightDemandProducts = new ArrayList<>();
        List<ProductEntity> productEntities = productRepo.findAll();
        Map<Long, Integer> map = new HashMap<>();

        productEntities.forEach(productEntity -> {
            List<DetailEntity> detailEntities = detailRepo.findAllByProductId(productEntity.getId());
            for (DetailEntity entity : detailEntities) {
                if (map.containsKey(entity.getProduct().getId())){
                    map.replace(entity.getProduct().getId(), map.get(entity.getProduct().getId())+1);
                }else {
                    map.put(entity.getProduct().getId(), 1);
                }
            }
        });

        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            if(entry.getValue() > 10){
                ResponsePaymentHightDemandProducts products = new ResponsePaymentHightDemandProducts();
                products.setProductCode(entry.getKey());
                products.setTotalNumber(entry.getValue());

                Optional<ProductEntity> productEntity = productRepo.findById(entry.getKey());
                ProductEntity productEntity1 = productEntity.get();
                products.setProductName(productEntity1.getName());
                responsePaymentHightDemandProducts.add(products);
            };
        }
        return responsePaymentHightDemandProducts;
    }

    @Override
    public List<ResponseBulkProducts> getBulkProducts() {
        List<ProductEntity> productEntities = productRepo.findAll();
        List<ResponseBulkProducts> results=new ArrayList<>();

        productEntities.forEach(entity -> {
            Integer sum=0;
            List<DetailEntity> detailEntities = detailRepo.findAllByProductId(entity.getId());
            for (DetailEntity en : detailEntities) {
                sum+=en.getQuantity();
            }
            if ((sum/2)>=8){
                ResponseBulkProducts responseBulkProducts = new ResponseBulkProducts();
                responseBulkProducts.setProductCode(entity.getId());
                responseBulkProducts.setPrice(entity.getPrice());
                results.add(responseBulkProducts);
            }
        });
        return results;
    }
}

