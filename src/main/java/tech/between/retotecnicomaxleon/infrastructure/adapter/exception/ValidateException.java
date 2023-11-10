package tech.between.retotecnicomaxleon.infrastructure.adapter.exception;


import lombok.NoArgsConstructor;
@NoArgsConstructor
public class ValidateException extends RuntimeException {

    public ValidateException(String message) {
        super(message);
    }
}
