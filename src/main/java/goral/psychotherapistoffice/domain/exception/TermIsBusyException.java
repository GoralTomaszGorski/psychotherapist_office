package goral.psychotherapistoffice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TermIsBusyException extends ResponseStatusException implements UserMessageExceptionInterface {

    public TermIsBusyException() {
        super(HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getUserMessage() {
        return "Termin który próbujesz zarezerwować jest już zajęty. Cofnij i wybierz zieloną ikonkę.";
    }
}
