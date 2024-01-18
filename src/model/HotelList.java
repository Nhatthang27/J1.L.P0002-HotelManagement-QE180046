/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import model.Hotel;
import util.MyUtil;

/**
 *
 * @author Nhatthang
 */
public class HotelList extends ArrayList<Hotel> implements Serializable {

    public HotelList() {
        super();
    }
    
    public HotelList(HotelList hotelList) {
        super();
        this.addAll(hotelList);
    }
    
    public Hotel deletedByID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(id)) {
                return this.remove(i);
            }
        }
        return null; 
    }
    
    public Hotel searchByID(String id) {
        for (Hotel hotel : this) {
            if (hotel.getId().equals(id)) {
                return hotel;
            }
        }
        return null;
    }

    public void update(Hotel newHotel) {
        Hotel oldHotel = searchByID(newHotel.getId());
        if (!newHotel.getName().isEmpty()) {
            oldHotel.setName(newHotel.getName());
        } 
        if (newHotel.getRoomAvail() >= 0) {
            oldHotel.setRoomAvail(newHotel.getRoomAvail());
        } 
        if (!newHotel.getAddress().isEmpty()) {
            oldHotel.setAddress(newHotel.getAddress());
        }
        if (!newHotel.getPhone().isEmpty()) {
            oldHotel.setPhone(newHotel.getPhone());
        }
        if (newHotel.getRating() > 0) {
            oldHotel.setRating(newHotel.getRating());
        }
    }
    
    public HotelList searchByName(String name) {
        HotelList sList = null;
        for (Hotel hotel : this) {
            if (hotel.getName().toLowerCase().contains(name.toLowerCase())) {
                if (sList == null) {
                    sList = new HotelList();
                }
                sList.add(hotel);
            }
        }
        return sList;
    }
}
