package nl.novi.eindopdrachtbackend.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import nl.novi.eindopdrachtbackend.model.Reservation;
import nl.novi.eindopdrachtbackend.model.User;

@Getter
@Setter
public class BookOutputDto {

    @GeneratedValue
    public Long isbn;
    @NotBlank
    public String author;
    @NotBlank
    public String title;

    public ReservationOutputDto setReservationOutput(Reservation reservation) {
        ReservationOutputDto shortReservation = new ReservationOutputDto();

        shortReservation.setReservationId(reservation.getReservationId());
        shortReservation.setDateOfReservation(reservation.getDateOfReservation());
        shortReservation.setReservationReady(reservation.isReservationReady());

        return shortReservation;
    }



}
