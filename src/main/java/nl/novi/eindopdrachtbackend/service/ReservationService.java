package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.ReservationDto;
import nl.novi.eindopdrachtbackend.model.Reservation;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ReservationService {


    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;

    private BookService bookService;

    public ReservationService(ReservationRepository reservationRepository, BookRepository bookRepository, BookService bookService) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    public ArrayList<ReservationDto> getReservation(Long reservationId) {

        ArrayList<Reservation> findReservation = reservationRepository.findReservationByReservationId(reservationId);
        ArrayList<ReservationDto> reservationContent = new ArrayList<>();
        for (Reservation reservation : findReservation){
            reservationContent.add(outputTransferReservationDtoto(reservation));
        }

        return reservationContent;
    }

//        ArrayList<Book> allBooksFromReservation = bookRepository.findByReservation(reservationNumber);
//        ArrayList<BookDto> allReservedBooks = new ArrayList<>();
//        for (Book book : allBooksFromReservation){
//            allReservedBooks.add(bookService.outputTransferBookToDto(book));
//        }
//        return allReservedBooks;
//    }


    public ReservationDto outputTransferReservationDtoto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        // de nieuwe dto vullen met de waardes uit Boo
        reservationDto.reservationId = reservation.getReservationId();
        reservationDto.dateOfReservation = reservation.getDateOfReservation();
        reservationDto.reservationReady = reservation.isReservationReady();
        reservationDto.reservedBooks = reservation.getBooks();


        return reservationDto;
    }

}
