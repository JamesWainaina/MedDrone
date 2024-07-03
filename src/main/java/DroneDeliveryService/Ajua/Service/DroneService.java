package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Dto.Drones.AvailableDronesDto;
import DroneDeliveryService.Ajua.Dto.Drones.RegisterDroneDto;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Models.Medication;


import java.util.List;
import java.util.UUID;

public interface DroneService {
    RegisterDroneDto registerDrone(RegisterDroneDto registerDroneDto);
    public void loadMedication(UUID serialNumber, List<Long> medicationIds, Double totalWeight);
    public List<Medication> getLoadedMedications(UUID serialNumber);
    List<AvailableDronesDto> getAvailableDrones(DroneState state);
    Integer checkDroneBatteryLevel(UUID serialNumber);
}
