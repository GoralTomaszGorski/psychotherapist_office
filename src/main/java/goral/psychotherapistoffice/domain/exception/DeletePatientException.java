package goral.psychotherapistoffice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeletePatientException extends ResponseStatusException implements UserMessageExceptionInterface {

    public DeletePatientException() {
        super(HttpStatus.BAD_REQUEST);    }

    @Override
    public String getUserMessage() {
        return "Nie można usunąć pacjenta. Pacjent jest umówiony na wizytę. Najpierw należy usunąć spotkanie/wizytę.";
    }
}
