package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.data.cartitem.domainObject.CartItemResponseData;
import com.fsse2401.project_backend.data.cartitem.domainObject.Result;
import com.fsse2401.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import com.fsse2401.project_backend.exception.cartItem.CartItemNotFoundException;
import com.fsse2401.project_backend.exception.cartItem.CartItemRemoveException;
import com.fsse2401.project_backend.repository.CartItemRepository;
import com.fsse2401.project_backend.service.CartItemService;
import com.fsse2401.project_backend.service.ProductService;
import com.fsse2401.project_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartItemService {
    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository){
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;


    }
    @Override
    public Result putCartItem(FirebaseUserData firebaseUserData,
                              Integer pid,
                              Integer quantity){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        ProductEntity productEntity = productService.getEntityByPid(pid);

        if (productEntity.getStock() >= quantity && quantity>0){

            Optional <CartItemEntity> optionalCartItemEntity =
                    cartItemRepository.findByProductAndUser(productEntity, userEntity);
            CartItemEntity cartItemEntity;
            if (optionalCartItemEntity.isEmpty()) {
                cartItemEntity = new CartItemEntity(userEntity, productEntity, quantity);
            }else{
                cartItemEntity = optionalCartItemEntity.get();
                cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
            }
            cartItemRepository.save(cartItemEntity);

            return Result.success;
        }
        return Result.fail;
    }

    @Override
    public List<CartItemResponseData> getUserCartItems(FirebaseUserData firebaseUserData){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
//        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(userEntity);
//        List<CartItemResponseData> cartItemResponseDataList = new ArrayList<>();
//
//        for (CartItemEntity cartItemEntity : cartItemEntityList)
//        {
//            CartItemResponseData cartItemResponseData = new CartItemResponseData(cartItemEntity);
//            cartItemResponseDataList.add(cartItemResponseData);
//        }
//
//        return cartItemResponseDataList;

        List<CartItemResponseData> responseDataList = new ArrayList<>();
        for (CartItemEntity cartItemEntity : getEntityListByUser(userEntity)){
            responseDataList.add(new CartItemResponseData(cartItemEntity));
        }
        return responseDataList;

    }

    @Override
    public CartItemResponseData upDateCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        ProductEntity productEntity = productService.getEntityById(pid);

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProductAndUser(productEntity, userEntity);
        CartItemEntity cartItemEntity;
        if (optionalCartItemEntity.isEmpty()){
            cartItemEntity = new CartItemEntity(userEntity, productEntity, quantity);
        } else {
            cartItemEntity = optionalCartItemEntity.get();
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
        }
        return new CartItemResponseData(cartItemRepository.save(cartItemEntity));

    }

    @Override
    public CartItemResponseData updateQuantity(FirebaseUserData firebaseUserData,
                                               Integer pid,
                                               Integer quantity){
        UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
        ProductEntity productEntity = productService.getEntityById(pid);

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProductAndUser(productEntity, userEntity);
        CartItemEntity cartItemEntity;
        if (optionalCartItemEntity.isEmpty()){
            cartItemEntity = new CartItemEntity(userEntity, productEntity, quantity);
        } else {
            cartItemEntity = optionalCartItemEntity.get();
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
        }
        return new CartItemResponseData(cartItemRepository.save(cartItemEntity));

    }

    public CartItemEntity getEntityByPidAndFirebaseUid(Integer pid, String firebaseUid){
        return cartItemRepository.findByProductPidAndUserFirebaseUid(
                pid, firebaseUid
        ).orElseThrow(() -> new CartItemNotFoundException(pid, firebaseUid));
    }

    @Override
    @Transactional
    public boolean removeCartItemByPid (FirebaseUserData firebaseUserData, Integer pid){
       int count = cartItemRepository.deleteByProduct_PidAndUser_FirebaseUid(pid,firebaseUserData.getFirebaseUid());
            if (count<=0){
                CartItemRemoveException ex = new CartItemRemoveException(pid, firebaseUserData.getFirebaseUid());
                logger.warn(ex.getMessage());
                throw ex;
            }
            return true;
    }

    @Override
    public List<CartItemEntity> getEntityListByUser(UserEntity userEntity) {
        return cartItemRepository.findAllByUser(userEntity);
    }

    @Override
    @Transactional
    public void emptyUserCart (String firebaseUid){
        cartItemRepository.deleteAllByUser_FirebaseUid(firebaseUid);
    }

}
