package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.data.product.domainObject.ProductResponseData;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.exception.product.ProductNotFoundException;
import com.fsse2401.project_backend.repository.ProductRepository;
import com.fsse2401.project_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseData> getAllProduct(){
    List<ProductResponseData> responseDataList = new ArrayList<>();

    for(ProductEntity entity:productRepository.findAll()){
        responseDataList.add(new ProductResponseData(entity));
    }
    return responseDataList;
    }

    @Override
    public ProductResponseData getByPid(Integer pid){
        ProductEntity productEntity = getEntityByPid(pid);
        return new ProductResponseData(productEntity);
    }

    @Override
    public ProductEntity getEntityByPid(Integer pid){
        return productRepository.findById(pid).orElseThrow(ProductNotFoundException::new);

    }

    @Override
    public ProductEntity getEntityById(Integer pid)
    {
        return productRepository.findById(pid).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public boolean isValidQuantity(ProductEntity entity, Integer quantity){
        if (quantity <1) {
            return false;
        }else if (quantity > entity.getStock()){
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidQuantity(Integer pid, Integer quantity){
        ProductEntity entity = getEntityByPid(pid);
        if (quantity <1) {
            return false;
        }else if (quantity > entity.getStock()){
            return false;
        }
        return true;
    }


    @Override
    public boolean deductStock(int pid, int amount){
        ProductEntity entity = getEntityByPid(pid);
        if (!isValidQuantity(entity, amount)){
            return false;
        }

        entity.setStock(entity.getStock() - amount);
        productRepository.save(entity);
        return true;
    }
}
