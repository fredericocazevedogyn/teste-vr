package br.com.frederico.vr.infrastructure.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@RestController
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllException (Exception ex, WebRequest request) {
        log.error("ERROR -> ", ex);
        ErrorMessage errorMessage = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CartaoJaExisteException.class)
    public final ResponseEntity<ErrorMessage> handleCartaoJaExisteException(Exception ex, WebRequest request) {
        log.error("ERROR -> ", ex);
        ErrorMessage errorMessage = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CartaoNaoExisteException.class)
    public final ResponseEntity<ErrorMessage> handleCartaoNaoExisteException(Exception ex, WebRequest request) {
        log.error("ERROR -> ", ex);
        ErrorMessage errorMessage = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public final ResponseEntity<ErrorMessage> handleSenhaInvalidaException(Exception ex, WebRequest request) {
        log.error("ERROR -> ", ex);
        ErrorMessage errorMessage = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public final ResponseEntity<ErrorMessage> handleSaldoInsuficienteException(Exception ex, WebRequest request) {
        log.error("ERROR -> ", ex);
        ErrorMessage errorMessage = new ErrorMessage(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
