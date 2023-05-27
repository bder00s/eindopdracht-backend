package nl.novi.eindopdrachtbackend.repository;

import nl.novi.eindopdrachtbackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long > {

    ResponseEntity<Book> findBookByIsbn(Long isbn);
    List<Book> findBooksByTitleContainingIgnoreCase(String title);
    List<Book> findBooksByAuthorContainingIgnoreCase(String author);

}
