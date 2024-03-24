package com.fsse2401.project_backend.data.transactionProduct.domainObject;

import com.fsse2401.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2401.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2401.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

public class TransactionProductResponseData {

    private Integer tpid;

    private Integer pid;

    private String name;

    private String description;
    private String imageUrl;


    private BigDecimal price;


    private Integer stock;

    private Integer quantity;

    public TransactionProductResponseData(TransactionProductEntity entity) {
    this.tpid = entity.getTpid();
    this.pid = entity.getPid();
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.imageUrl = entity.getImageUrl();
    this.price = entity.getPrice();
    this.stock = entity.getStock();
    this.quantity = entity.getQuantity();

    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
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
