package DroneDeliveryService.Ajua.Contollers;

import DroneDeliveryService.Ajua.Exception.AuditException;
import DroneDeliveryService.Ajua.Service.DroneAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/audit")
public class DroneAuditController {
    @Autowired
    private DroneAuditService droneAuditService;

    public DroneAuditController(DroneAuditService droneAuditService) {
        this.droneAuditService = droneAuditService;
    }

    public ResponseEntity<String> runAudit(){
        try {
            droneAuditService.auditBatteryLevels();;
            return ResponseEntity.ok("Drone audit completed successfully.");
        }catch (AuditException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
