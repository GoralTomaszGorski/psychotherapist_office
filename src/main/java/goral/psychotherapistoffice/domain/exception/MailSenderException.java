package goral.psychotherapistoffice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MailSenderException extends ResponseStatusException implements UserMessageExceptionInterface {
    public MailSenderException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String getUserMessage() {
        return "Nie można wysłać maila. Sprawdź czy Twój mail został wpisany poprawnie, lub zadzwoń do terapeuty";
    }
}
