package com.exhibitions.entity;

import java.util.Date;
import java.util.Objects;

public class Exposition {

    private int id;
    private String name;
    private int price;
    private Date data_time;
    private Date period;
    private String rooms;

    public Exposition() {
    }

    public Exposition(int id, String name, int price, Date data_time, Date period, String rooms) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.data_time = data_time;
        this.period = period;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getData_time() {
        return data_time;
    }

    public void setData_time(Date data_time) {
        this.data_time = data_time;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposition that = (Exposition) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(data_time, that.data_time) &&
                Objects.equals(period, that.period) &&
                Objects.equals(rooms, that.rooms);
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", data_time=" + data_time +
                ", period=" + period +
                ", rooms=" + rooms +
                '}';
    }
}
