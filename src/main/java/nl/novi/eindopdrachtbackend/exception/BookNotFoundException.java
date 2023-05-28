package nl.novi.eindopdrachtbackend.exception;


    public class BookNotFoundException extends RuntimeException //Alles wat gebeurt tijdens het runnen - fouten je nog wel kan opvangen door Exception te schrijven
    {
        public BookNotFoundException(){
            super();
        }
        public BookNotFoundException(String message){

            super(message);
        }

    }

