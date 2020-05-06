package maestro.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    public InvalidJwtAuthenticationException(String expired_or_invalid_jwt_token) {
        super(expired_or_invalid_jwt_token);
    }
}
