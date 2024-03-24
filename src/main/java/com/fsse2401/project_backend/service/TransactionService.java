package com.fsse2401.project_backend.service;

import com.fsse2401.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;

public interface TransactionService {
    TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getByTid(FirebaseUserData firebaseUserData, Integer tid);

    boolean payTransaction(FirebaseUserData firebaseUserData, Integer tid);

    TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid);
}
