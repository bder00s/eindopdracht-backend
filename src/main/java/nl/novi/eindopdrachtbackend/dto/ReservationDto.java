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


    public LocalDate dateOfReservation;

    @NotNull
    public boolean reservationReady;

   public List<Book> reservedBooks;

    public User user;


}
