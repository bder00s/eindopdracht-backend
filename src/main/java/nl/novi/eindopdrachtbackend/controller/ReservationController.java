package nl.novi.eindopdrachtbackend.controller;

import nl.novi.eindopdrachtbackend.dto.ReservationDto;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.ReservationRepository;
import nl.novi.eindopdrachtbackend.service.BookService;
import nl.novi.eindopdrachtbackend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    private BookService bookService;
    private BookRepository bookRepository;

    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository, BookService bookService, BookRepository bookRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/content/{reservationId}")
    public ResponseEntity<ArrayList<ReservationDto>> getReservationContent(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.getReservation(reservationId));
    }

}
