package DroneDeliveryService.Ajua.Contollers;

import DroneDeliveryService.Ajua.Exception.AuditException;
import DroneDeliveryService.Ajua.Service.DroneAuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

class DroneAuditControllerTest {

    @Mock
    private DroneAuditService droneAuditService;

    @InjectMocks
    private DroneAuditController droneAuditController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void runAudit_Success() {
        doNothing().when(droneAuditService).auditBatteryLevels();

        ResponseEntity<String> response = droneAuditController.runAudit();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Drone audit completed successfully.", response.getBody());
    }

    @Test
    void runAudit_AuditException() {
        String errorMessage = "Audit failed due to an error.";
        doThrow(new AuditException(errorMessage)).when(droneAuditService).auditBatteryLevels();

        ResponseEntity<String> response = droneAuditController.runAudit();

        // Verify the response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
    }
}