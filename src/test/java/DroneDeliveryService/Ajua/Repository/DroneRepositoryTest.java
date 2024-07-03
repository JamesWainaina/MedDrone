package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Models.Drone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class DroneRepositoryTest {

    @Autowired
    private DroneRepository droneRepository;

    @Test
    void findBySerialNumber() {
        Drone drone = new Drone();
        drone.setSerialNumber(UUID.randomUUID());
        drone.setModel(DroneModel.Heavyweight);
        drone.setWeightLimit(500.0);
        drone.setBatteryCapacity(100);
        drone.setState(DroneState.IDLE);

        // Save the Drone entity
        droneRepository.save(drone);

        // Test findBySerialNumber method
        Optional<Drone> foundDroneOptional = droneRepository.findBySerialNumber(drone.getSerialNumber());
        assertTrue(foundDroneOptional.isPresent());
        assertEquals(drone.getSerialNumber(), foundDroneOptional.get().getSerialNumber());
    }


    @Test
    void findByStateAndBatteryLevel() {
        Drone drone1 = new Drone();
        UUID serialNumber1 = UUID.randomUUID();
        drone1.setSerialNumber(serialNumber1);
        drone1.setBatteryCapacity(30);
        drone1.setState(DroneState.IDLE);

        Drone drone2 = new Drone();
        UUID serialNumber2 = UUID.randomUUID();
        drone2.setSerialNumber(serialNumber2);
        drone2.setBatteryCapacity(35);
        drone2.setState(DroneState.IDLE);

        List<Drone> drones = Arrays.asList(drone1, drone2);

        when(droneRepository.findByStateAndBatteryLevel(DroneState.IDLE, 25)).thenReturn(drones);

        List<Drone> result = droneRepository.findByStateAndBatteryLevel(DroneState.IDLE, 25);
        assertEquals(2, result.size());
        assertEquals(serialNumber1, result.get(0).getSerialNumber());
        assertEquals(serialNumber2, result.get(1).getSerialNumber());
    }
}