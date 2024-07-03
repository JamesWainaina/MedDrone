package DroneDeliveryService.Ajua.Contollers;

import DroneDeliveryService.Ajua.Dto.Medication.CreateMedicationDto;
import DroneDeliveryService.Ajua.Dto.Medication.GetMedicationDto;
import DroneDeliveryService.Ajua.Service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;


    //create-medication
    @PostMapping("/create-medication")
    public ResponseEntity<?> createMedication(@Valid @RequestBody CreateMedicationDto createMedicationDto){
        return  new ResponseEntity<>(medicationService.createMedication(createMedicationDto), HttpStatus.CREATED);
    }

    //Get medication
    @GetMapping("/get-medication")
    public ResponseEntity<?> getMedication(@RequestBody @Valid String medicationName){
        return new ResponseEntity<>(medicationService.getMedication(medicationName), HttpStatus.OK);
    }

    //delete medication
    @DeleteMapping("/delete-medication/{name}")
    public ResponseEntity<?> deleteMedication(@PathVariable String medicationName){
        return new ResponseEntity<>(medicationService.deleteMedication(medicationName), HttpStatus.OK);
    }


    //Getting the medication loaded on drone
    @GetMapping("/byDrone/{serialNumber}")
    public ResponseEntity<List<GetMedicationDto>> getMedicationsByDrone(@PathVariable UUID serialNumber){
        List<GetMedicationDto> medications = medicationService.getMedicationsByDrone(serialNumber);
        return new ResponseEntity<>(medications, HttpStatus.OK);

    }
}
