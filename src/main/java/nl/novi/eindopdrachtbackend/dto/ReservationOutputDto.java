package nl.novi.eindopdrachtbackend.dto;


import lombok.Getter;
import lombok.Setter;
import nl.novi.eindopdrachtbackend.model.Book;
import nl.novi.eindopdrachtbackend.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReservationOutputDto {

    public Long reservationId;
    public LocalDate dateOfReservation;
    public boolean reservationReady;
    public List<Book> reservedBooks;
    public User user;



    public List<Book> setBooksOutput(List<Book> shortList) {
        List<Book> reservedBooks = new ArrayList<>();

        for (int i = 0; i < shortList.size(); i++) {
            Book newBook = new Book();
            newBook.setIsbn(shortList.get(i).getIsbn());
            newBook.setTitle(shortList.get(i).getTitle());
            newBook.setAuthor(shortList.get(i).getAuthor());
            reservedBooks.add(newBook);
        }
        return reservedBooks;
    }

//    public User getUserOutput(User shortUser) {
//        return shortUser.getUsername() + "\n" +
//                shortUser.getEmail() + "\n" +
//                shortUser.getFullname();
//    }

}

