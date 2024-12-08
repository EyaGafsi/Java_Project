package org.example.demo1.services;

import org.example.demo1.entities.Reservation;

import java.util.List;

public interface ReservationService {
    void deleteReservation(Reservation reservation);
    void updateReservation(Reservation reservation);
    List<Reservation> getReservations();
    void addReservation(Reservation reservation);
}
