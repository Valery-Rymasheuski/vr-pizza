package com.example.rymasheuski.valery.vrpizza.model;

import com.example.rymasheuski.valery.vrpizza.model.CartItem;

import java.util.List;

/**
 * Created by valery on 16.8.18.
 */

public class Order {

    private String street;
    private String house;
    private String flat;
    private String phone;
    private String email;
    private String deliveryTime;
    private String coupon;
    private String comment;

    private Integer cityId;
    private Integer paymentTypeId;

    private List<CartItem> cartItems;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", flat='" + flat + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", coupon='" + coupon + '\'' +
                ", comment='" + comment + '\'' +
                ", cityId=" + cityId +
                ", paymentTypeId=" + paymentTypeId +
                ", cartItems=" + (cartItems != null ? cartItems.size() : 0) +
                '}';
    }
}
