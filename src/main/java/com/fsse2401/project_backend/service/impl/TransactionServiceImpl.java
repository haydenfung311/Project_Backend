package com.fsse2401.project_backend.service.impl;

import com.fsse2401.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.cartitem.status.TransactionStatus;
import com.fsse2401.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import com.fsse2401.project_backend.exception.transaction.PayTransactionException;
import com.fsse2401.project_backend.exception.transaction.PrepareTransactionException;
import com.fsse2401.project_backend.exception.transaction.TransactionNotFoundException;
import com.fsse2401.project_backend.repository.TransactionRepository;
import com.fsse2401.project_backend.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final ProductService productService;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(ProductService productService, UserService userService, CartItemService cartItemService, TransactionProductService transactionProductService, TransactionRepository transactionRepository) {
        this.productService = productService;
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData) {
        try {
            UserEntity userEntity = userService.getEntityByFirebaseUserData(firebaseUserData);
            List<CartItemEntity> userCart = cartItemService.getEntityListByUser(userEntity);
            if (userCart.isEmpty()) {
                throw new PrepareTransactionException("Cart is Empty!");
            }

            TransactionEntity transactionEntity = new TransactionEntity(userEntity);
            transactionEntity = transactionRepository.save(transactionEntity);
            List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();
            for (CartItemEntity cartItemEntity: userCart){
                TransactionProductEntity transactionProductEntity = transactionProductService.createTransactionProduct(transactionEntity, cartItemEntity);

                transactionProductEntityList.add(transactionProductEntity);
                transactionEntity.setTotal(transactionEntity.getTotal().add(
                        transactionProductEntity.getPrice().multiply(
                                new BigDecimal(transactionProductEntity.getQuantity())
                        )
                ));
            }

            transactionEntity = transactionRepository.save(transactionEntity);
            return new TransactionResponseData(transactionEntity, transactionProductEntityList);

        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public TransactionResponseData getByTid(FirebaseUserData firebaseUserData, Integer tid){
try {
TransactionEntity transactionEntity = getEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());
List<TransactionProductEntity> transactionProductEntityList = transactionProductService.getEntityListByTransaction(transactionEntity);
return new TransactionResponseData(transactionEntity, transactionProductEntityList);
}catch (Exception ex){
        logger.warn("Get transaction fail" + ex.getMessage());
        throw ex;
}
    }

    @Override
    public boolean payTransaction(FirebaseUserData firebaseUserData, Integer tid){
        try{
            TransactionEntity transactionEntity = getEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());
            if (transactionEntity.getStatus()!= TransactionStatus.PREPARE){
            throw new PayTransactionException("Status Error");
            }
            List<TransactionProductEntity> transactionProductEntityList = transactionProductService.getEntityListByTransaction(transactionEntity);
        for(TransactionProductEntity transactionProductEntity: transactionProductEntityList){
            if (!productService.isValidQuantity(transactionProductEntity.getPid(), transactionProductEntity.getQuantity())){
                throw new PayTransactionException("No enough stock"+ transactionProductEntity.getPid());
            }
        }

        for (TransactionProductEntity transactionProductEntity: transactionProductEntityList){
            productService.deductStock(transactionProductEntity.getPid(), transactionProductEntity.getQuantity());
        }

        transactionEntity.setStatus(TransactionStatus.PROCESSING);
        transactionRepository.save(transactionEntity);
        return true;
        }catch (Exception ex){
            logger.warn("Pay transaction fail" + ex.getMessage());
            throw ex;
        }
    }

    @Override
    public TransactionResponseData finishTransaction(FirebaseUserData firebaseUserData, Integer tid){
        try{
            TransactionEntity transactionEntity = getEntityByTidAndFirebaseUid(tid, firebaseUserData.getFirebaseUid());
            if (transactionEntity.getStatus() != TransactionStatus.PROCESSING) {
                throw new PayTransactionException("Status Error");
            }

            cartItemService.emptyUserCart(firebaseUserData.getFirebaseUid());
            transactionEntity.setStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transactionEntity);
            return new TransactionResponseData(
                    transactionEntity,
                    transactionProductService.getEntityListByTransaction(transactionEntity)
            );
        }catch (Exception ex){
            logger.warn("Finish transaction failed" + ex.getMessage());
            throw ex;
        }

    }

    public TransactionEntity getEntityByTidAndFirebaseUid(Integer tid, String firebaseUid){
        return transactionRepository.findByTidAndUser_FirebaseUid(tid, firebaseUid).orElseThrow(
                () -> new TransactionNotFoundException(tid, firebaseUid));
    }
}
