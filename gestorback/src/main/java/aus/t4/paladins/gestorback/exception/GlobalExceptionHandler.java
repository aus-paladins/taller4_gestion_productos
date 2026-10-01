package aus.t4.paladins.gestorback.exception;

import aus.t4.paladins.gestorback.dto.ErrorResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolation(
      DataIntegrityViolationException exception) {

    ErrorResponseDTO error = new ErrorResponseDTO(
        HttpStatus.CONFLICT.value(),
        "La operación no se pudo completar porque los datos entran en conflicto con información existente."
    );

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleException(Exception exception) {

    ErrorResponseDTO error = new ErrorResponseDTO(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Ocurrió un error inesperado en el servidor."
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}