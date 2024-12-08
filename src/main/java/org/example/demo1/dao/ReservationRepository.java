package org.example.demo1.dao;

import org.example.demo1.entities.Reservation;

public class ReservationRepository extends GenericDAO<Reservation> {
    public ReservationRepository() {
        super(Reservation.class);
    }
}
