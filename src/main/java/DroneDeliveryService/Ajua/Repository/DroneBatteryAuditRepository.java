package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Models.DroneBatteryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DroneBatteryAuditRepository extends JpaRepository<DroneBatteryAudit, Long> {
    List<DroneBatteryAudit> findBySerialNumber(UUID serialNumber);

}
