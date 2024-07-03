package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Models.Drone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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

    }
}