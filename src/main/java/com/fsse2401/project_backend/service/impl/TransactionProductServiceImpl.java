package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.project_backend.repository.TransactionProductRepository;
import com.fsse2401.project_backend.service.TransactionProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository){
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity){
        TransactionProductEntity transactionProductEntity = new TransactionProductEntity(transactionEntity, cartItemEntity);
        return transactionProductRepository.save(transactionProductEntity);
    }

    @Override
    public List<TransactionProductEntity>getEntityListByTransaction(TransactionEntity transactionEntity){
            return transactionProductRepository.findAllByTransaction(transactionEntity);
    }
}
