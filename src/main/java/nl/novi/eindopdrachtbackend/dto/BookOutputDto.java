package nl.novi.eindopdrachtbackend.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookOutputDto {

    @GeneratedValue
    public Long isbn;
    @NotBlank
    public String author;
    @NotBlank
    public String title;



}
