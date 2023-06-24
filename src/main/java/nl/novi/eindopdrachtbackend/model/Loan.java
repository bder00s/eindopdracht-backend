package nl.novi.eindopdrachtbackend.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Loan {

    @OneToOne(mappedBy = "loan")
    User user;

    @OneToMany(mappedBy = "loan")
    List<Book> bookList;

    @Id
    @GeneratedValue
    Long loanId;



}
