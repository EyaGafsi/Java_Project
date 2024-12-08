package org.example.demo1.services.servicesImpl;

import org.example.demo1.dao.ReservationRepository;
import org.example.demo1.entities.Reservation;
import org.example.demo1.services.ReservationService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    ReservationRepository reservationRepository = new ReservationRepository();
    public void deleteReservation(Reservation reservation) {
        Reservation optReservation=reservationRepository.getById(reservation.getId_reservation());
        if(optReservation!=null) {
            reservationRepository.delete(optReservation);
        }
    }
    public void updateReservation(Reservation reservation) {
        Reservation optReservation=reservationRepository.getById(reservation.getId_reservation());
        if(optReservation!=null) {
            reservationRepository.update(optReservation);
        }
    }
    public void addReservation(Reservation reservation) {

        reservationRepository.add(reservation);

    }
    public List<Reservation> getReservations() {
        return reservationRepository.getAll();
    }
}
