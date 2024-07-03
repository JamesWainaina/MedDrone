package DroneDeliveryService.Ajua.Contollers;


import DroneDeliveryService.Ajua.Dto.Drones.AvailableDronesDto;
import DroneDeliveryService.Ajua.Dto.Drones.LoadDroneDto;
import DroneDeliveryService.Ajua.Dto.Drones.RegisterDroneDto;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Exception.DroneException;
import DroneDeliveryService.Ajua.Models.Medication;
import DroneDeliveryService.Ajua.Service.DroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/drones")
public class DroneController {
    @Autowired
    private DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }


    // registering a drone
    @PostMapping("/register")
    public ResponseEntity<RegisterDroneDto> registerDrone(@RequestBody @Valid RegisterDroneDto registerDroneDto) {
        RegisterDroneDto registeredDrone = droneService.registerDrone(registerDroneDto);
        return new ResponseEntity<>(registeredDrone, HttpStatus.CREATED);
    }


    //loading a drone with medications
    @PostMapping("/{serialNumber}/load")
    public ResponseEntity<?> loadMedication(
            @PathVariable UUID serialNumber,
            @RequestBody LoadMedicationRequest request) {
        try {
            droneService.loadMedication(serialNumber, request.getMedicationIds(),
                    request.getTotalWeight());
            return ResponseEntity.ok("Medications loaded successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Inner class to represent the request body for loading medications
    static class LoadMedicationRequest {
        private List<Long> medicationIds;
        private double totalWeight;

        // Getters and setters
        public List<Long> getMedicationIds() {
            return medicationIds;
        }

        public void setMedicationIds(List<Long> medicationIds) {
            this.medicationIds = medicationIds;
        }

        public double getTotalWeight() {
            return totalWeight;
        }

        public void setTotalWeight(double totalWeight) {
            this.totalWeight = totalWeight;
        }
    }


    // Getting the loaded medications
    @GetMapping("/{serialNumber}/medications")
    public ResponseEntity<List<Medication>> getLoadedMedications(@PathVariable UUID serialNumber) {
        try {
            List<Medication> medications = droneService.getLoadedMedications(serialNumber);
            return ResponseEntity.ok(medications);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    // Getting available drones for loading
    @GetMapping("/available-drones")
    public ResponseEntity<List<AvailableDronesDto>> getAvailableDrones(@RequestParam(required = false)
                                                                           String state){
        DroneState droneState;

        try {
            droneState = DroneState.valueOf(state.toUpperCase());
        }catch (IllegalArgumentException e){
            droneState = DroneState.IDLE;
        }
        List<AvailableDronesDto> availableDrones = droneService.getAvailableDrones(droneState);
        return ResponseEntity.ok(availableDrones);
    }




    // checking drones battery level
    @GetMapping("/{serialNumber}")
    public ResponseEntity<Map<String, Integer>> checkDroneBatteryLevel(@PathVariable UUID serialNumber) {
        Integer batteryLevel = droneService.checkDroneBatteryLevel(serialNumber);
        Map<String, Integer> response = new HashMap<>();
        response.put("batteryLevel", batteryLevel);
        return ResponseEntity.ok(response);
    }

}
