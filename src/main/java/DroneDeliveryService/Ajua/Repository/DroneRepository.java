package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<Drone, UUID> {
    Optional<Drone> findBySerialNumber(UUID serialNumber);

    @Query("SELECT d FROM Drone d WHERE d.state = :state AND d.batteryCapacity >= :batteryLevel")
    List<Drone> findByStateAndBatteryLevel(@Param("state") DroneState state, @Param("batteryLevel") int batteryLevel);
}
