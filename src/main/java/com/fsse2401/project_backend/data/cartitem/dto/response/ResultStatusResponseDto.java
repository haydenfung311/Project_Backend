package com.fsse2401.project_backend.data.cartitem.dto.response;

import com.fsse2401.project_backend.data.cartitem.domainObject.Result;

public class ResultStatusResponseDto {
    private String result;

    public ResultStatusResponseDto(Result result) {
        this.result = result.name();
    }

    public String getResult() {
        return result;
    }

    private void setResult(String result) {
        this.result = result;
    }
}
