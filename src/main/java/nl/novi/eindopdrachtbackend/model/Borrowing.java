package nl.novi.eindopdrachtbackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Borrowing {

    @OneToMany(mappedBy = "borrowing")
    List<Book> bookList;

    @Id
    @GeneratedValue
    Long borrowingId;



}
