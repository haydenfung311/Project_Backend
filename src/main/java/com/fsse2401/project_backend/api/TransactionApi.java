package com.fsse2401.project_backend.api;

import com.fsse2401.project_backend.Util.JwtUtil;
import com.fsse2401.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2401.project_backend.data.transaction.response.dto.TransactionResponseDto;
import com.fsse2401.project_backend.data.transaction.response.dto.TransactionSuccessResponseDto;
import com.fsse2401.project_backend.service.TransactionService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionApi {

    private final TransactionService transactionService;

    public TransactionApi (TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @PostMapping
    public TransactionResponseDto prepareTransaction (JwtAuthenticationToken token){
        TransactionResponseData responseData = transactionService.prepareTransaction(JwtUtil.getFirebaseUserData(token));
        return new TransactionResponseDto(responseData);

    }

    @GetMapping("/{tid}")
public TransactionResponseDto getTransactionByTid (JwtAuthenticationToken token, @PathVariable Integer tid){
        return new TransactionResponseDto(
                transactionService.getByTid(JwtUtil.getFirebaseUserData(token), tid)
        );
    }

    @PatchMapping("/{tid}/pay")
    public TransactionSuccessResponseDto payTransaction (JwtAuthenticationToken token,
                                                         @PathVariable Integer tid){
        transactionService.payTransaction(JwtUtil.getFirebaseUserData(token), tid);
        return new TransactionSuccessResponseDto();
    }

    @PatchMapping("/{tid}/finish")
    public TransactionResponseDto finishTransactionByTid (JwtAuthenticationToken token,
                                                          @PathVariable Integer tid) {
        return new TransactionResponseDto(
                transactionService.finishTransaction(JwtUtil.getFirebaseUserData(token), tid)
        );
    }

}
