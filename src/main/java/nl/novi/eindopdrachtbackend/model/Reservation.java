package nl.novi.eindopdrachtbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reservations")

public class Reservation {


    @OneToMany(mappedBy = "reservation")
    List<Book> bookList;

    @Id
    @GeneratedValue
    private Long reservationId;


}

