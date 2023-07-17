package nl.novi.eindopdrachtbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate dateOfReservation;

    @Column
    private boolean reservationReady;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
//    private User user;


    @OneToMany(mappedBy = "reservation")
    @Column
    List<Book> reservationContent;


    public Reservation() {

    }
}

