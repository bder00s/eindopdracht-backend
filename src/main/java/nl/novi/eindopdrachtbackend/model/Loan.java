package nl.novi.eindopdrachtbackend.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Loan {

    @Id
    @GeneratedValue
    Long loanId;

//    @OneToOne(mappedBy = "loan")
//    User user;

//    @OneToMany(mappedBy = "loan")
//    List<Book> bookList;
//


}
