package com.fsse2401.project_backend.data.transactionProduct.entity;

import com.fsse2401.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "Transaction_Product")
public class TransactionProductEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @ManyToOne
    @JoinColumn (name = "tid", nullable = false)
    private TransactionEntity transaction;

    @Column (nullable = false)
    private Integer pid;
    @Column (nullable = false)
    private String name;

    private String description;
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer quantity;

    public TransactionProductEntity() {
    }

    public TransactionProductEntity(TransactionEntity transaction, CartItemEntity cartItemEntity) {
    this.transaction = transaction;
    this.pid = cartItemEntity.getProduct().getPid();
    this.name = cartItemEntity.getProduct().getName();
    this.description = cartItemEntity.getProduct().getDescription();
    this.imageUrl = cartItemEntity.getProduct().getImageUrl();
    this.price = cartItemEntity.getProduct().getPrice();
    this.stock = cartItemEntity.getProduct().getStock();
    this.quantity = cartItemEntity.getQuantity();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public TransactionEntity getTransactionEntity() {
        return transaction;
    }

    public void setTransactionEntity(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
