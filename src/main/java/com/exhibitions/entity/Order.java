package com.exhibitions.entity;

import java.util.Objects;
import java.util.Optional;

public class Order {

    private Integer id;
    private Integer user_id;
    private  Integer billing;
    private Integer orderStatus_id;
    private Integer exposition_id;

    public Order() {
    }

    public Order(Integer id,  Integer user_id, Integer billing, Integer orderStatus_id, Integer exposition_id) {
        this.id = id;
        this.user_id = user_id;
        this.billing = billing;
        this.orderStatus_id = orderStatus_id;
        this.exposition_id = exposition_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser() {
        return user_id ;
    }

    public void setUser(Integer user_id) {
        this.user_id = user_id;
    }

    public int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public Integer getOrderStatus_id() {
        return orderStatus_id;
    }

    public void setOrderStatus_id(Integer orderStatus_id) {
        this.orderStatus_id = orderStatus_id;
    }

    public Integer getExposition() {
        return exposition_id;
    }

    public void setExposition(Integer exposition) {
        this.exposition_id = exposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                billing == order.billing &&
                Objects.equals(user_id, order.user_id) &&
                Objects.equals(orderStatus_id, order.orderStatus_id) &&
                Objects.equals(exposition_id, order.exposition_id);
    }

    @Override
    public String toString() {
        return "OrderDao{" +
                "id=" + id +
                ", user_id" + user_id +
                ", billing=" + billing +
                ", orderStatus=" + orderStatus_id +
                ", exposition=" + exposition_id +
                '}';
    }
}
