package goral.psychotherapistoffice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeleteCalenderException extends ResponseStatusException implements UserMessageExceptionInterface {
    public DeleteCalenderException() {
            super(HttpStatus.BAD_REQUEST);
    }


    @Override
        public String getUserMessage() {
            return "Nie można usunąć terminu. Pacjent jest umuwiony w tym terminie na wizytę. Najpierw trzeba usunąć tą wizytę";
        }

}
