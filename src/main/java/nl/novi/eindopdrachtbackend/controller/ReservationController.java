package nl.novi.eindopdrachtbackend.controller;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtbackend.dto.BookDto;
import nl.novi.eindopdrachtbackend.dto.ReservationDto;
import nl.novi.eindopdrachtbackend.repository.BookRepository;
import nl.novi.eindopdrachtbackend.repository.ReservationRepository;
import nl.novi.eindopdrachtbackend.service.BookService;
import nl.novi.eindopdrachtbackend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static nl.novi.eindopdrachtbackend.extraMethods.Stringbuilder.getStringbuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Object> addReservation(@Valid @RequestBody ReservationDto reservationDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return getStringbuilder(bindingResult);
        } else {
            String reservationinfo = reservationService.newReservation(reservationDto);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + reservationinfo).toUriString());

            return ResponseEntity.created(uri).body(reservationinfo);
        }
    }

    @PutMapping("/connectBooks/{reservationId}/{isbn}")
    public ResponseEntity<Object> assignBookToReservation(@PathVariable ("reservationId") Long reservationId, @PathVariable ("isbn") Long isbn){
        bookService.assignBookToReservation(reservationId, isbn);
        return ResponseEntity.noContent().build();
    }

}
