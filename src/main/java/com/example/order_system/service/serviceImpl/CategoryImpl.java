package com.example.order_system.service.serviceImpl;

import com.example.order_system.dto.CategoryDto;
import com.example.order_system.entity.CategoryEntity;
import com.example.order_system.entity.ProductEntity;
import com.example.order_system.repository.CategoryRepo;
import com.example.order_system.repository.ProductRepo;
import com.example.order_system.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public ResponseEntity<List<CategoryDto>> getUsers() {
        List<CategoryEntity> categoryEntities=categoryRepo.findAll();
        List<CategoryDto> categoryDtos=new ArrayList<>();
        categoryEntities.forEach(categoryEntity -> {
            CategoryDto dto=new CategoryDto();
            BeanUtils.copyProperties(categoryEntity, dto);
            categoryDtos.add(dto);
        });


        return ResponseEntity.ok(categoryDtos);
    }

    @Override
    public ResponseEntity<CategoryDto> getUser(Long productId) {
        Optional<ProductEntity> productEntity=productRepo.findById(productId);
        if (productEntity.isPresent()){
            Long categoryId = productEntity.get().getCategoryId();
            Optional<CategoryEntity> categoryEntity = categoryRepo.findById(categoryId);
            if (categoryEntity.isPresent()){
                CategoryEntity entity = categoryEntity.get();
                CategoryDto dto=new CategoryDto();
                BeanUtils.copyProperties(entity, dto);
                return ResponseEntity.ok(dto);
            }
        }
        return null;
    }


}
