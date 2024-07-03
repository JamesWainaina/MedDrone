package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Dto.Medication.CreateMedicationDto;
import DroneDeliveryService.Ajua.Dto.Medication.DeleteMedicationDto;
import DroneDeliveryService.Ajua.Dto.Medication.GetMedicationDto;
import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.Medication;

import java.util.List;
import java.util.UUID;

public interface MedicationService {
    CreateMedicationDto createMedication(CreateMedicationDto createMedicationRequest);
    GetMedicationDto getMedication(String  medicationName);
    DeleteMedicationDto deleteMedication(String  medicationName);
    List<GetMedicationDto> getMedicationsByDrone(UUID serialNumber);
}
