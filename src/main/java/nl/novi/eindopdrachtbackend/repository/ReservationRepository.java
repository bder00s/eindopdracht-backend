package nl.novi.eindopdrachtbackend.repository;

import nl.novi.eindopdrachtbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    ArrayList<Reservation>findReservationByReservationId(Long reservationId);
}
