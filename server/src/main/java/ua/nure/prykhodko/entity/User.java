package ua.nure.prykhodko.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    public String id;

    @Column(name = "name")
    public String name;

    @Column(name = "surname")
    public String surname;

    @Column(name = "access")
    public Boolean access;

    @Column(name = "photo")
    public String photo;

    @Column(name = "room")
    public int room;

    @Column(name = "group")
    public String group;

    @Column(name = "adress_of_residence")
    public String adressOfResidence;

    @Column(name = "position")
    public String position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAdressOfResidence() {
        return adressOfResidence;
    }

    public void setAdressOfResidence(String adressOfResidence) {
        this.adressOfResidence = adressOfResidence;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
