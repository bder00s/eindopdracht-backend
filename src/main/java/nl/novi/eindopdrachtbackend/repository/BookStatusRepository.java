package nl.novi.eindopdrachtbackend.repository;

import nl.novi.eindopdrachtbackend.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStatusRepository extends JpaRepository <BookStatus, Long> {
}

