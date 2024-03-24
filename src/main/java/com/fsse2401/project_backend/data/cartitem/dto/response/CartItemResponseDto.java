package com.fsse2401.project_backend.data.cartitem.dto.response;
import com.fsse2401.project_backend.data.cartitem.domainObject.CartItemResponseData;

import java.math.BigDecimal;

public class CartItemResponseDto {

    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private Integer cartQuantity;
    private Integer stock;

    public CartItemResponseDto(CartItemResponseData cartItemResponseData) {
        this.pid = cartItemResponseData.getPid();
        this.name = cartItemResponseData.getName();
        this.description = cartItemResponseData.getDescription();
        this.imageUrl = cartItemResponseData.getImageUrl();
        this.price = cartItemResponseData.getPrice();
        this.cartQuantity = cartItemResponseData.getCartQuantity();
        this.stock = cartItemResponseData.getStock();
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

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
