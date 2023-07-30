package nl.novi.eindopdrachtbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservations")

public class Reservation {

    @Id
    @GeneratedValue
    @Column
    private Long reservationId;

    @Column
    private LocalDate dateOfReservation = LocalDate.now();

    @Column
    private boolean reservationReady;

    public Reservation(Long reservationId, LocalDate dateOfReservation, boolean reservationReady, User user, List<Book> reservedBooks) {
        this.reservationId = reservationId;
        this.dateOfReservation = dateOfReservation;
        this.reservationReady = reservationReady;
        this.user = user;
        this.reservedBooks = reservedBooks;
    }
    @OneToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "reservation")
    @Column
    List<Book> reservedBooks;

    public List<Book>getBooks(){
        return reservedBooks;
    }

    public User getOwnerOfReservation(){
        return user;
    }

    public void setOwnerOfReservation(User user) {
        this.user = user;
    }

    public Reservation() {

    }
}

