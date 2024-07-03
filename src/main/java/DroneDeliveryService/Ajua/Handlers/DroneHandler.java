package DroneDeliveryService.Ajua.Handlers;

import DroneDeliveryService.Ajua.Exception.DroneException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class DroneHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleDroneException(DroneException droneException) {
        return new ResponseEntity<>(droneException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
