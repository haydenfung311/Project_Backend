package com.fsse2401.project_backend.data.cartitem.dto.response;

public class CartItemSuccessResponseDto {
    private String result;
    public CartItemSuccessResponseDto() {setResult("Success");}

    public String getResult(){return result;}

    private void setResult (String result) {this.result=result;}
}
