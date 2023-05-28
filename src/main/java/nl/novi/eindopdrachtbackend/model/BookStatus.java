package nl.novi.eindopdrachtbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book_status")
public class BookStatus {

    @OneToOne(mappedBy = "bookStatus")
    Book book;

    @Id
    @GeneratedValue
    Long BookStatusId;

    Boolean available;
}
