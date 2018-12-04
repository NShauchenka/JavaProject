package exceptionhandlers;

import static controllers.Application.getDEBUG_LOGGER;

public class InvalidDataException extends Exception {

    public InvalidDataException() {
        super();
    }
    
    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}
