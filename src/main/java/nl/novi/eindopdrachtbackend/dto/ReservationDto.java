package nl.novi.eindopdrachtbackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.model.User;

import java.time.LocalDate;
import java.util.List;

public class ReservationDto {

    @GeneratedValue
    public Long reservationId;

    @NotNull
    public LocalDate dateOfReservation;

    @NotNull
    public boolean reservationReady;

    public Book book;
//
//    public User user;


}
