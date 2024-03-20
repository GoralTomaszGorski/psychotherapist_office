package goral.psychotherapistoffice.domain.exception;

import org.springframework.http.HttpStatus;

public class CalenderNotFoundException extends RuntimeException implements UserMessageExceptionInterface{

    public CalenderNotFoundException(HttpStatus BAD_REQUEST) {
        super();
    }

    @Override
    public String getUserMessage() {
        return "Nie można odszukać takiego terminu, spróbuj po innym id";
    }

}
