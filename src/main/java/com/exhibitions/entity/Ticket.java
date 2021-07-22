package com.exhibitions.entity;

import java.util.Date;
import java.util.Objects;

public class Ticket {
    private Integer numberTicket;
    private String nameExposition;
    private Date startExposition;
    private Date periodExposition;
    private String roomsOnExhibition;
    private Integer priceTicket;

    public Ticket(){};

    public Ticket(Integer numberTicket, String nameExposition, Date startExposition, Date periodExposition, String roomsOnExhibition, Integer priceTicket) {
        this.numberTicket = numberTicket;
        this.nameExposition = nameExposition;
        this.startExposition = startExposition;
        this.periodExposition = periodExposition;
        this.roomsOnExhibition = roomsOnExhibition;
        this.priceTicket = priceTicket;
    }

    public Integer getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(Integer numberTicket) {
        this.numberTicket = numberTicket;
    }

    public String getNameExposition() {
        return nameExposition;
    }

    public void setNameExposition(String nameExposition) {
        this.nameExposition = nameExposition;
    }

    public Date getStartExposition() {
        return startExposition;
    }

    public void setStartExposition(Date startExposition) {
        this.startExposition = startExposition;
    }

    public Date getPeriodExposition() {
        return periodExposition;
    }

    public void setPeriodExposition(Date periodExposition) {
        this.periodExposition = periodExposition;
    }

    public String getRoomsOnExhibition() {
        return roomsOnExhibition;
    }

    public void setRoomsOnExhibition(String roomsOnExhibition) {
        this.roomsOnExhibition = roomsOnExhibition;
    }

    public Integer getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Integer priceTicket) {
        this.priceTicket = priceTicket;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "numberTicket=" + numberTicket +
                ", nameExposition='" + nameExposition + '\'' +
                ", startExposition=" + startExposition +
                ", periodExposition=" + periodExposition +
                ", roomsOnExhibition='" + roomsOnExhibition + '\'' +
                ", priceTicket=" + priceTicket +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(numberTicket, ticket.numberTicket) &&
                Objects.equals(nameExposition, ticket.nameExposition) &&
                Objects.equals(startExposition, ticket.startExposition) &&
                Objects.equals(periodExposition, ticket.periodExposition) &&
                Objects.equals(roomsOnExhibition, ticket.roomsOnExhibition) &&
                Objects.equals(priceTicket, ticket.priceTicket);
    }

}
