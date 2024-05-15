package net.youss.fontendreactclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {
    // @ExceptionHandler Pour gerer specifiquement 'NoSuchElementException'
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException
                                  (NoSuchElementException ex, WebRequest request) {
        System.out.println("test " + ex);
        String path = request.getDescription(false).replace("uri=", "");
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .path(path)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
