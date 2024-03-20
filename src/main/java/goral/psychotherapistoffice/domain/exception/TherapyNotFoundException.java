package goral.psychotherapistoffice.domain.exception;


public class TherapyNotFoundException extends RuntimeException implements UserMessageExceptionInterface{

    public TherapyNotFoundException() {

    }

    @Override
        public String getUserMessage() {
            return "Nie można odszukać takiej terapii, spróbuj po innym id";
        }
}

