package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.DroneBatteryAudit;
import DroneDeliveryService.Ajua.Repository.DroneBatteryAuditRepository;
import DroneDeliveryService.Ajua.Repository.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DroneAuditServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private DroneBatteryAuditRepository droneBatteryAuditRepository;

    @InjectMocks
    private DroneAuditService droneAuditService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void auditBatteryLevels() {
        Drone drone1 = new Drone();
        drone1.setSerialNumber(UUID.randomUUID());
        drone1.setBatteryCapacity(100);

        Drone drone2 = new Drone();
        drone2.setSerialNumber(UUID.randomUUID());
        drone2.setBatteryCapacity(90);

        List<Drone> drones = Arrays.asList(drone1, drone2);

        // Mock behavior of droneRepository.findAll()
        when(droneRepository.findAll()).thenReturn(drones);

        droneAuditService.auditBatteryLevels();
        verify(droneBatteryAuditRepository, times(2)).save(any(DroneBatteryAudit.class));
    }
}