package DroneDeliveryService.Ajua.Handlers;

import DroneDeliveryService.Ajua.Exception.MedicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
public class MedicationHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> conflictHandler(MedicationException medicationException){
        return  new ResponseEntity<>(medicationException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
