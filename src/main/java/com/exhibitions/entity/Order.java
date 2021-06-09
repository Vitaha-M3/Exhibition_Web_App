package com.exhibitions.entity;

import java.util.Objects;
import java.util.Optional;

public class Order {

    private int id;
    private Optional<User> user;
    private  int billing;
    private OrderStatus orderStatus;
    private Optional<Exposition> expositionId;

    public Order() {
    }

    public Order(int id, User user, int billing, OrderStatus orderStatus, Exposition exposition) {
        this.id = id;
        this.user = Optional.ofNullable(user);
        this.billing = billing;
        this.orderStatus = orderStatus;
        this.expositionId = Optional.ofNullable(exposition);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Optional<User> getUser() {
        return user ;
    }

    public void setUser(Optional<User> user) {
        this.user = user;
    }

    public int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public String getOrderStatus() {
        return orderStatus.getStatus();
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Optional<Exposition> getExposition() {
        return expositionId;
    }

    public void setExposition(Optional<Exposition> exposition) {
        this.expositionId = exposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                billing == order.billing &&
                Objects.equals(user, order.user) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(expositionId, order.expositionId);
    }

    @Override
    public String toString() {
        return "OrderDao{" +
                "id=" + id +
                ", user_id" + user.get() +
                ", billing=" + billing +
                ", orderStatus=" + orderStatus +
                ", exposition=" + expositionId +
                '}';
    }
}
