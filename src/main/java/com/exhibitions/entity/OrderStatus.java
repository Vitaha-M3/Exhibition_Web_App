package com.exhibitions.entity;

public class OrderStatus {

    private int id;
    private String status;

    public OrderStatus() {
    }

    public OrderStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public OrderStatus (int id){
        this.id = id;
        switch (id){
            case 1: this.status = "В обработке";
            case 2: this.status = "Оплачено";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
