package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.dto.ReservationDto;
import nl.novi.eindopdrachtbackend.dto.ReservationOutputDto;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.exception.ReservationNotFoundException;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.model.Reservation;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.ReservationRepository;
import nl.novi.eindopdrachtbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {


    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;

    private UserRepository userRepository;

    private BookService bookService;

    public ReservationService(ReservationRepository reservationRepository, BookRepository bookRepository, UserRepository userRepository, BookService bookService) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
    }

    public ArrayList<ReservationOutputDto> getReservation(Long reservationId) {
        ArrayList<Reservation> findReservation = reservationRepository.findReservationByReservationId(reservationId);
        ArrayList<ReservationOutputDto> reservationContent = new ArrayList<>();
        for (Reservation reservation : findReservation) {
            reservationContent.add(simpleOutput(reservation));
        }
        return reservationContent;
    }


    public void assignUserToReservation(Long reservationId, String username) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        var optionalUser = userRepository.findById(username);

        if (optionalReservation.isPresent() && optionalUser.isPresent()) {
            var reservation = optionalReservation.get();
            var user = optionalUser.get();

            reservation.setOwnerOfReservation(user);
            reservationRepository.save(reservation);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Reservation/User not found!");
        }
    }


    public String newReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();


        reservation.setReservationId(reservationDto.reservationId);
        reservation.setDateOfReservation(LocalDate.now());
        reservation.setReservationReady(reservationDto.reservationReady);

        reservationRepository.save(reservation);



        // koppel boeken aan reservering
        for (Book book : reservationDto.reservedBooks) {
            bookService.assignBookToReservation(reservation.getReservationId(), book.getIsbn());
        }
        reservation.setReservedBooks(reservationDto.reservedBooks);
//
        reservationRepository.save(reservation);

        return "Reservation saved to database:" +
                "\n reservation id: " + reservation.getReservationId() +
                "\n date: " + reservation.getDateOfReservation() +
                "\n reservation ready? " + reservation.isReservationReady() + "\n content of reservation: " + reservation.getBooks(); //GEEFT NOG NIET DE TITEL VAN BOEKEN WEER - ALLEEN DTO INFO
    }


    public void updateReservation(Long reservationId, ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ReservationNotFoundException("Reservation not found!"));

        reservation.setReservationId(reservationDto.reservationId);
        reservation.setDateOfReservation(LocalDate.now());
        reservation.setReservationReady(reservationDto.reservationReady);

        
        reservationRepository.save(reservation);

        // koppel boeken aan reservering
        for (Book book : reservationDto.reservedBooks) {
            bookService.assignBookToReservation(reservation.getReservationId(), book.getIsbn());
        }
        reservation.setReservedBooks(reservationDto.reservedBooks);


        reservationRepository.save(reservation);
    }


    public void deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ReservationNotFoundException("Reservation not found!")) ;

        List<Book> listOfBooks = reservation.getBooks();
        for (Book book : listOfBooks) {

            book.setReservation(null);
            reservation.setOwnerOfReservation(null);
            reservationRepository.save(reservation);
            bookRepository.save(book);
        }

        reservationRepository.deleteById(reservationId);

    }

    public ReservationOutputDto simpleOutput(Reservation reservation){
        ReservationOutputDto reservationOutputDto = new ReservationOutputDto();

        reservationOutputDto.reservationId = reservation.getReservationId();
        reservationOutputDto.dateOfReservation = reservation.getDateOfReservation();
        reservationOutputDto.reservationReady = reservation.isReservationReady();
        reservationOutputDto.reservedBooks = reservationOutputDto.setBooksOutput(reservation.getReservedBooks());
        reservationOutputDto.user = reservationOutputDto.setUserOutput(reservation.getUser());


        return reservationOutputDto;
    }







    // OUTPUT TRANSFER DTO
    public ReservationDto outputTransferReservationtoDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        // de nieuwe dto vullen met de waardes uit Reservation
        reservationDto.reservationId = reservation.getReservationId();
        reservationDto.dateOfReservation = reservation.getDateOfReservation();
        reservationDto.reservationReady = reservation.isReservationReady();
        reservationDto.reservedBooks = reservation.getBooks();
//        reservationDto.user = reservation.getOwnerOfReservation();


        return reservationDto;
    }



    // INPUT TRANSFER DTO
    public Reservation inputTransferDtoToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();

        reservation.setReservationId(reservationDto.reservationId);
        reservation.setDateOfReservation(reservationDto.dateOfReservation);
        reservation.setReservationReady(reservationDto.reservationReady);
        reservation.setReservedBooks(reservationDto.reservedBooks);



        return reservation;
    }

}
