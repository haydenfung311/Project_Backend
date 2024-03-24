package com.fsse2401.project_backend.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartItemRemoveException extends RuntimeException{
    public CartItemRemoveException(Integer pid, String firebaseUid){
        super(String.format("CartItem remove failed-pid:%d, firebaseUid:%s", pid, firebaseUid));
    }
}
