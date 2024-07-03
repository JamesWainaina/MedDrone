package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Optional<Medication> findByMedicationName(String medicationName);
    void deleteByMedicationName(String medicationName);
    List<Medication> findByDroneSerialNumber(UUID serialNumber);
    Optional<Medication> findByCode(String code);
}
