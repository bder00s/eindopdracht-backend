package nl.novi.eindopdrachtbackend.exception;

public class ReservationNotFoundException extends RuntimeException{
    public ReservationNotFoundException(){
        super();
    }
    public ReservationNotFoundException(String message){

        super(message);
    }


}
