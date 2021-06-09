package com.exhibitions.entity;

public class Room {
    private int id_room;
    private String room;

    public Room() {
    }

    public Room(int id_room, String room) {
        this.id_room = id_room;
        this.room = room;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id_room == room.id_room &&
                this.room.equals(room.room);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id_room +
                ", room_number='" + room + '\'' +
                '}';
    }
}
