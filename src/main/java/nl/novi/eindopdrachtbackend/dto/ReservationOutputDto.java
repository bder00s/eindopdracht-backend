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
    public List<BookOutputDto> reservedBooks;
    public UserOutputDto user;



    public List<BookOutputDto> setBooksOutput(List<Book> shortList) {
        List<BookOutputDto> reservedBooks = new ArrayList<>();

        for (int i = 0; i < shortList.size(); i++) {
            BookOutputDto shortBook = new BookOutputDto();
            shortBook.setIsbn(shortList.get(i).getIsbn());
            shortBook.setTitle(shortList.get(i).getTitle());
            shortBook.setAuthor(shortList.get(i).getAuthor());
            reservedBooks.add(shortBook);
        }
        return reservedBooks;
    }

    public UserOutputDto setUserOutput(User user) {
        UserOutputDto shortUser = new UserOutputDto();

        shortUser.setUsername(user.getUsername());
        shortUser.setEmail(user.getEmail());
        shortUser.setFullname(user.getFullname());

        return shortUser;
    }

}

