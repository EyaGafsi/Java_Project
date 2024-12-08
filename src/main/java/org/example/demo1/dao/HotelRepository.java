package org.example.demo1.dao;

import org.example.demo1.entities.Hotel;

public class HotelRepository extends GenericDAO<Hotel> {
    public HotelRepository() {
        super(Hotel.class);
    }
}
