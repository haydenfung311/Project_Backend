package com.fsse2401.project_backend.service;

import com.fsse2401.project_backend.data.cartitem.domainObject.CartItemResponseData;
import com.fsse2401.project_backend.data.cartitem.domainObject.Result;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {

    Result putCartItem(FirebaseUserData firebaseUserData,
                       Integer pid,
                       Integer quantity);

    List<CartItemResponseData> getUserCartItems(FirebaseUserData firebaseUserData);

    CartItemResponseData upDateCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    CartItemResponseData updateQuantity(FirebaseUserData firebaseUserData,
                                        Integer pid,
                                        Integer quantity);

    @Transactional
    boolean removeCartItemByPid(FirebaseUserData firebaseUserData, Integer pid);

    List<CartItemEntity> getEntityListByUser (UserEntity userEntity);

    void emptyUserCart(String firebaseUid);
}
