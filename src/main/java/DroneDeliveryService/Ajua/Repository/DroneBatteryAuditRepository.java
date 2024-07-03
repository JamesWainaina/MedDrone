package DroneDeliveryService.Ajua.Repository;

import DroneDeliveryService.Ajua.Models.DroneBatteryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryAuditRepository extends JpaRepository<DroneBatteryAudit, Long> {
}
