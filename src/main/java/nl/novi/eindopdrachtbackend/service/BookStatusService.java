package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.BookStatusDto;
import nl.novi.eindopdrachtbackend.model.BookStatus;
import nl.novi.eindopdrachtbackend.repository.BookStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class BookStatusService {

    private final BookStatusRepository bookStatusRepository;

    // INJECTION //
    public BookStatusService(BookStatusRepository bookStatusRepository) {
        this.bookStatusRepository = bookStatusRepository;
    }

    // ADD BOOK STATUS - POST METHODE //
// FOUTMELDING //////////////////////////////////////
    public String createBookStatus(BookStatusDto bookStatusDto){
        // VAN KLANT -> DATABASE -> INPUT //
        BookStatus bookStatus = new BookStatus();
        bookStatus.setBookStatusId(bookStatusDto.BookStatusId);
        bookStatus.setAvailable(bookStatusDto.available);

        bookStatusRepository.save(bookStatus);
        String outcome = "Saved to library database:" +
                "\n BookStatus Id: " + bookStatus.getBookStatusId() +
                "\n Available? : " + bookStatus.getAvailable();
        return outcome;
    }
}

