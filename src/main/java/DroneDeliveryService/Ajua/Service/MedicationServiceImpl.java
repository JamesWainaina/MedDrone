package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Dto.Medication.CreateMedicationDto;
import DroneDeliveryService.Ajua.Dto.Medication.DeleteMedicationDto;
import DroneDeliveryService.Ajua.Dto.Medication.GetMedicationDto;
import DroneDeliveryService.Ajua.Exception.MedicationException;
import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.Medication;
import DroneDeliveryService.Ajua.Repository.MedicationRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements  MedicationService{

    //dependency injection
    private MedicationRepository medicationRepository;
    private  final ModelMapper modelMapper;


    //constructor for medicationRepository and modelMapper
    public MedicationServiceImpl(MedicationRepository medicationRepository, ModelMapper modelMapper){
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    // create medication function
    @Override
    public CreateMedicationDto createMedication(CreateMedicationDto createMedicationRequest) {
        if (createMedicationRequest == null) throw  new MedicationException("Request cannot be null");
        Optional<Medication> foundMedication = medicationRepository.findByMedicationName(
                createMedicationRequest.getMedicationName());
        if (foundMedication.isPresent()) throw new MedicationException("Medication name already exists!");
        Medication newMed = modelMapper.map(createMedicationRequest, Medication.class);
        Medication savedMed = medicationRepository.save(newMed);
        return modelMapper.map(savedMed, CreateMedicationDto.class);
        }

        // get medication function
    @Override
    public GetMedicationDto getMedication(String medicationName) {
        if (medicationName == null) throw  new MedicationException("Request cannot be null");
        Optional<Medication> foundMedication = medicationRepository.findByMedicationName(medicationName);
        if (foundMedication.isEmpty()) throw  new MedicationException("Medication not found");
        return modelMapper.map(foundMedication.get(), GetMedicationDto.class);
    }

    //deleting the medication by name
    @Override
    public DeleteMedicationDto deleteMedication(String medicationName) {
        DeleteMedicationDto deleteMedicationDto = null;
        try {
            getMedication(medicationName);
            medicationRepository.deleteByMedicationName(medicationName);
            String message = "Medication deleted successfully";
            deleteMedicationDto = new DeleteMedicationDto(message, true);
        }
        catch (MedicationException e){
            if (e.getMessage().equals("Medication does not exist")) {
                deleteMedicationDto  = new DeleteMedicationDto(e.getMessage(), false);
                return  deleteMedicationDto;
            }
        }
        return deleteMedicationDto;
    }

    @Override
    public List<GetMedicationDto> getMedicationsByDrone(UUID SerialNumber) {
        List<Medication> medications = medicationRepository.findByDroneSerialNumber(SerialNumber);
        return medications.stream()
                .map(medication -> modelMapper.map(medication, GetMedicationDto.class))
                .collect(Collectors.toList());
    }
}
