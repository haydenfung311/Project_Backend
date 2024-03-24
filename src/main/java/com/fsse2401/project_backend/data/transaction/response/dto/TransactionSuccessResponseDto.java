package com.fsse2401.project_backend.data.transaction.response.dto;

public class TransactionSuccessResponseDto {
    private String result;
    public TransactionSuccessResponseDto() {setResult("Success");}

    public String getResult(){return result;}

    private void setResult (String result) {this.result=result;}
}
