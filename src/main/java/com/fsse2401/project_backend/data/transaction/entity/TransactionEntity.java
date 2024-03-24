package com.fsse2401.project_backend.data.transaction.entity;

import com.fsse2401.project_backend.data.cartitem.status.TransactionStatus;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn (name ="buyer_uid", nullable = false)
    private UserEntity user;
    @Column (nullable = false)
    private LocalDateTime dateTime;

    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column (nullable = false)
    private BigDecimal total;


    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity user) {
        this.user=user;
        this.dateTime = LocalDateTime.now();
        this.status = TransactionStatus.PREPARE;
        this.total = BigDecimal.ZERO;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime datetime) {
        this.dateTime = datetime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
