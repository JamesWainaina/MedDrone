package DroneDeliveryService.Ajua.Components;

import DroneDeliveryService.Ajua.Service.DroneAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatteryAuditChecker {

    @Autowired
    private DroneAuditService droneBatteryAuditService;

    // for every hour
    @Scheduled(fixedRate = 3600000)
    public void performBatteryCheck() {
        droneBatteryAuditService.auditBatteryLevels();
    }

}
