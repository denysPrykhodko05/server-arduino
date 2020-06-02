package ua.nure.prykhodko.entity;

import java.sql.Timestamp;

public class Guests {

    public int id;

    public String name;

    public String surname;

    public int room;

    public Timestamp visitTime;

    public String owner;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Timestamp getVisitTime() {
        return visitTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setVisitTime(Timestamp visitTime) {
        this.visitTime = visitTime;
    }
}
