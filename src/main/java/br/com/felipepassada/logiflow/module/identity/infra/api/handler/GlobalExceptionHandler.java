package br.com.felipepassada.logiflow.module.identity.infra.api.handler;

import br.com.felipepassada.logiflow.module.common.domain.exception.DomainException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorDetails> handleDomainException(DomainException ex) {
        var details = new ErrorDetails(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }

    // Trata erros de "Não Encontrado" (como o .orElseThrow())
    @ExceptionHandler(EntityNotFoundException.class) // Ou a sua RuntimeException de busca
    public ResponseEntity<ErrorDetails> handleNotFound(RuntimeException ex) {
        var details = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    // Record auxiliar para o JSON de erro
    public record ErrorDetails(int status, String message, long timestamp) {}

}
