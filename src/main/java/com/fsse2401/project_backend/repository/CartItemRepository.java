package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.product.entity.ProductEntity;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository <CartItemEntity, Integer> {
    List<CartItemEntity> findAllByUser(UserEntity userEntity);
    Optional<CartItemEntity> findByProductAndUser(ProductEntity productEntity, UserEntity userEntity);

    Optional<CartItemEntity> findByProductPidAndUserFirebaseUid(Integer pid, String firebaseUid);

    Integer deleteByProduct_PidAndUser_FirebaseUid(Integer pid, String firebaseUid);


    void deleteAllByUser_FirebaseUid(String firebaseUid);
}