package goral.psychotherapistoffice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class DeleteTherapyException extends ResponseStatusException implements UserMessageExceptionInterface {


    public DeleteTherapyException(HttpStatus BAD_REQUEST) {
        super(HttpStatus.BAD_REQUEST);
    }

    @Override
        public String getUserMessage() {
            return "Nie można usunąć Tearpii. Ten rodzaj terapii jest użyty do umuwionej wizyty. Najpierw trzeba usunąć tą wizytę";
        }

}
