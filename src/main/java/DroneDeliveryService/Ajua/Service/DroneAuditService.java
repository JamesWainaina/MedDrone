package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.DroneBatteryAudit;
import DroneDeliveryService.Ajua.Repository.DroneBatteryAuditRepository;
import DroneDeliveryService.Ajua.Repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DroneAuditService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private DroneBatteryAuditRepository droneBatteryAuditRepository;

    public DroneAuditService(DroneRepository droneRepository,
                             DroneBatteryAuditRepository droneBatteryAuditRepository){
        this.droneRepository = droneRepository;
        this.droneBatteryAuditRepository = droneBatteryAuditRepository;

    }

    public void auditBatteryLevels(){
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones){
            DroneBatteryAudit audit = DroneBatteryAudit.builder()
                    .timestamp(LocalDateTime.now())
                    .batteryCapacity((drone.getBatteryCapacity()))
                    .serialNumber(drone.getSerialNumber())
                    .drone(drone)
                    .build();
            droneBatteryAuditRepository.save(audit);
        }
    }

}
