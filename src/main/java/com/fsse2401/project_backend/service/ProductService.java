package com.fsse2401.project_backend.service;

import com.fsse2401.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.product.domainObject.ProductResponseData;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductResponseData> getAllProduct();

    ProductResponseData getByPid(Integer pid);

    ProductEntity getEntityByPid(Integer pid);

    ProductEntity getEntityById(Integer pid);

    boolean isValidQuantity (ProductEntity entity, Integer quantity);

    boolean isValidQuantity(Integer pid, Integer quantity);

    boolean deductStock(int pid, int amount);
}
