package com.fsse2401.project_backend.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(Integer pid, String firebaseUid){
        super(String.format("CartItem not found-pid:%d, firebaseUid:%s", pid, firebaseUid));
    }
}
