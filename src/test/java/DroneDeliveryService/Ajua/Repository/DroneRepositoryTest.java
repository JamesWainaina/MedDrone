package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Models.Drone;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Transactional
class DroneRepositoryTest {

    @Autowired
    private DroneRepository droneRepository;

    @Test
    void findBySerialNumber() {
        UUID serialNumber = UUID.randomUUID();
        Drone drone = Drone.builder()
                .serialNumber(serialNumber)
                .model(DroneModel.Lightweight)
                .weightLimit(100.0)
                .batteryCapacity(60)
                .state(DroneState.IDLE)
                .build();

        droneRepository.save(drone);


        Optional<Drone> foundDroneOptional = droneRepository
                .findBySerialNumber(serialNumber);
        assertThat(foundDroneOptional).isPresent();
        assertThat(foundDroneOptional.get()).isEqualTo(drone);
    }


    @Test
    void findByStateAndBatteryLevel() {
        Drone drone1 = new Drone();
        UUID serialNumber1 = UUID.randomUUID();
        drone1.setSerialNumber(serialNumber1);
        drone1.setBatteryCapacity(30);
        drone1.setState(DroneState.IDLE);


        List<Drone> drones = Arrays.asList(drone1);

        droneRepository.findByStateAndBatteryLevel(DroneState.IDLE,30);

        List<Drone> result = droneRepository.findByStateAndBatteryLevel(DroneState.IDLE, 25);
        assertEquals(1, result.size());
    }
}