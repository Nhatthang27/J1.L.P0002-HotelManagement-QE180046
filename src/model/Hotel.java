/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Nhatthang
 */
public class Hotel implements Serializable{

    private String id;
    private String name;
    private int roomAvail;
    private String address;
    private String phone;
    private int rating;

    public Hotel() {
    }

    public Hotel(String id, String name, int roomAvail, String address, String phone, int rating) {
        this.id = id;
        this.name = name;
        this.roomAvail = roomAvail;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

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

    public int getRoomAvail() {
        return roomAvail;
    }

    public void setRoomAvail(int roomAvail) {
        this.roomAvail = roomAvail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public static String getHeader() {
        return String.format("|%-8s|%-15s|%-20s|%-70s|%-11s|%-12s|",
                "Hotel_id", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
    }

    @Override
    public String toString() {
        return String.format("|%-8s|%-15s|%-20d|%-70s|%-11s|%3d %-8s|",
                id, name, roomAvail, address, phone, rating, "stars");
    }
}
