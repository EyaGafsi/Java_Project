package org.example.demo1.services;

import org.example.demo1.entities.Hotel;

import java.util.List;

public interface HotelService {
    void deleteHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    List<Hotel> getHotels();
    void addHotel(Hotel hotel);
}
