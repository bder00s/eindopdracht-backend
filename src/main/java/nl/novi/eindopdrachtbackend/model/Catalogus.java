package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.*;
import nl.novi.eindopdrachtbackend.enummeration.Genre;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalogus")

public class Catalogus {

 @OneToMany(mappedBy = "catalogus")
 ArrayList<Book> allBooks;

 Genre genre;

    @Id
    @GeneratedValue
    Long catalogusId;


}
