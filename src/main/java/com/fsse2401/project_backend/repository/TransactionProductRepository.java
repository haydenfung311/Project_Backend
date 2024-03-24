package com.fsse2401.project_backend.repository;

import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionProductRepository extends CrudRepository <TransactionProductEntity, Integer> {
List<TransactionProductEntity> findAllByTransaction(TransactionEntity transaction);

}
