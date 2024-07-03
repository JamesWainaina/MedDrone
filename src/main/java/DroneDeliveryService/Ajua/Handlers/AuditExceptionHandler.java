package DroneDeliveryService.Ajua.Handlers;

import DroneDeliveryService.Ajua.Exception.AuditException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuditExceptionHandler {

    @ExceptionHandler(AuditException.class)
    public ResponseEntity<?> handleAuditException(AuditException auditException) {
        return new ResponseEntity<>(auditException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
