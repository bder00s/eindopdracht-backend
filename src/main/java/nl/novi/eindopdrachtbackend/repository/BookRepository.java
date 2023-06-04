package nl.novi.eindopdrachtbackend.repository;

import nl.novi.eindopdrachtbackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long > {

    // MEERDERE BOEKEN ZOEKEN
    ArrayList<Book> findBookByIsbn(Long isbn);
    ArrayList<Book> findBooksByTitleContainingIgnoreCase(String title);
    ArrayList<Book> findBooksByAuthorContainingIgnoreCase(String author);


}

