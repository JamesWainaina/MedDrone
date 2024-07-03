package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.DroneBatteryAudit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class DroneBatteryAuditRepositoryTest {
    @Autowired
    private DroneBatteryAuditRepository droneBatteryAuditRepository;

    @Test
    public void testSaveAndFindByDroneSerialNumber() {
        // Create a new Drone entity
        Drone drone = new Drone();
        UUID serialNumber = UUID.randomUUID();
        drone.setSerialNumber(serialNumber);
        drone.setBatteryCapacity(100);

        // Create a new DroneBatteryAudit entity
        DroneBatteryAudit audit = new DroneBatteryAudit();
        audit.setDrone(drone);
        audit.setTimestamp(LocalDateTime.now());
        audit.setBatteryCapacity(drone.getBatteryCapacity());

        // Save the audit entity
        droneBatteryAuditRepository.save(audit);

        // Retrieve audits by drone serial number
        List<DroneBatteryAudit> audits = droneBatteryAuditRepository.findBySerialNumber(serialNumber);

        // Assert that the saved audit entity was found
        assertFalse(audits.isEmpty());
        assertEquals(100, audits.get(0).getBatteryCapacity());
    }

}