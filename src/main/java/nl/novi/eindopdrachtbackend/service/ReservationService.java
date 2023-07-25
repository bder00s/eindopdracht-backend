package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.dto.ReservationDto;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.model.Reservation;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        for (Reservation reservation : findReservation) {
            reservationContent.add(outputTransferReservationtoDto(reservation));
        }

        return reservationContent;
    }

    public String newReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
//        Book book = new Book();

        reservation.setReservationId(reservationDto.reservationId);
        reservation.setDateOfReservation(LocalDate.now());
        reservation.setReservationReady(reservationDto.reservationReady);
        reservation.setReservedBooks(reservationDto.reservedBooks);

//        reservationRepository.save(reservation);

//        for (Book book: reservation.getReservedBooks()) {
//            bookService.assignBookToReservation(reservation.getReservationId(), book.getIsbn());
//        }


        reservationRepository.save(reservation);

        return "Reservation saved to database:" +
                "\n reservation id: " + reservation.getReservationId() +
                "\n date: " + reservation.getDateOfReservation() +
                "\n reservation ready? " + reservation.isReservationReady() + "\n content of reservation: " + reservation.getBooks();
    }







    public ReservationDto outputTransferReservationtoDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        // de nieuwe dto vullen met de waardes uit Reservation
        reservationDto.reservationId = reservation.getReservationId();
        reservationDto.dateOfReservation = reservation.getDateOfReservation();
        reservationDto.reservationReady = reservation.isReservationReady();
        reservationDto.reservedBooks = reservation.getBooks();

        return reservationDto;
    }

}
