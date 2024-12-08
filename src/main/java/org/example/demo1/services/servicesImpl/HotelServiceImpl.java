package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.HotelRepository;
import org.example.demo1.entities.Hotel;
import org.example.demo1.services.HotelService;

import java.util.List;

public class HotelServiceImpl implements HotelService {
    HotelRepository hotelRepository = new HotelRepository();
    public void deleteHotel(Hotel hotel) {
        Hotel optHotel=hotelRepository.getById(hotel.getId_hotel());
        if(optHotel!=null) {
            hotelRepository.delete(optHotel);
        }
    }
    public void updateHotel(Hotel hotel) {
        Hotel optHotel=hotelRepository.getById(hotel.getId_hotel());
        if(optHotel!=null) {
            hotelRepository.update(optHotel);
        }
    }
    public void addHotel(Hotel hotel) {

        hotelRepository.add(hotel);

    }
    public List<Hotel> getHotels() {
        return hotelRepository.getAll();
    }
}
