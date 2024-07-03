package DroneDeliveryService.Ajua.Models;

import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DroneBatteryAuditTest {

    private DroneBatteryAudit batteryAudit;

    @BeforeEach
    public void setUp() {
        UUID serialNumber = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        Drone drone = new Drone(serialNumber, DroneModel.Heavyweight, 500.0, 90,
                DroneState.LOADED, null, null);

        batteryAudit = DroneBatteryAudit.builder()
                .id(1L)
                .serialNumber(serialNumber)
                .batteryCapacity(80)
                .timestamp(timestamp)
                .drone(drone)
                .build();
    }
    @Test
    void getId() {
        assertEquals(1L, batteryAudit.getId());
    }

    @Test
    void setId() {
        batteryAudit.setId(2L);
        assertEquals(2L, batteryAudit.getId());
    }

    @Test
    void getSerialNumber() {
        assertEquals(batteryAudit.getSerialNumber(), batteryAudit.getSerialNumber());
    }

    @Test
    void setSerialNumber() {
        UUID newSerialNumber = UUID.randomUUID();
        batteryAudit.setSerialNumber(newSerialNumber);
        assertEquals(newSerialNumber, batteryAudit.getSerialNumber());
    }

    @Test
    void getBatteryCapacity() {
        assertEquals(80, batteryAudit.getBatteryCapacity());
    }

    @Test
    void setBatteryCapacity() {
        batteryAudit.setBatteryCapacity(75);
        assertEquals(75, batteryAudit.getBatteryCapacity());
    }

    @Test
    void getTimestamp() {
        assertNotNull(batteryAudit.getTimestamp());
    }

    @Test
    void setTimestamp() {
        LocalDateTime newTimestamp = LocalDateTime.of(2023, 1, 1, 10, 30);
        batteryAudit.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, batteryAudit.getTimestamp());
    }

    @Test
    void getDrone() {
        assertNotNull(batteryAudit.getDrone());
    }

    @Test
    void setDrone() {
        Drone newDrone = new Drone(UUID.randomUUID(), DroneModel.Middleweight, 400.0, 85, DroneState.DELIVERING, null, null);
        batteryAudit.setDrone(newDrone);
        assertEquals(newDrone, batteryAudit.getDrone());
    }

    @Test
    void builder() {
        assertNotNull(batteryAudit);
    }
}