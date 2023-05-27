package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "catalogus")

public class Catalogus {

 @OneToMany(mappedBy = "catalogus")
 List<Book> bookList;



    @Id
    @GeneratedValue
    Long catalogusId;


}
