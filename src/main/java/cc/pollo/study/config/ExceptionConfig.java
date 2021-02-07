package cc.pollo.study.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Configuration for handling exceptions thrown in controllers
 */
@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<?> handleProcessingError(JsonProcessingException exception) {
        return ResponseEntity.status(400).body("Unable to parse question JSON");
    }

}