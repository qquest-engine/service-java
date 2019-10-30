package ua.ithillel.evo.questengine.exception;

public class AuthenticationException extends ApplicationGlobalException {

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

