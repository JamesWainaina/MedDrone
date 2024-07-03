package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Contollers.DroneController;
import DroneDeliveryService.Ajua.Dto.Drones.AvailableDronesDto;
import DroneDeliveryService.Ajua.Dto.Drones.RegisterDroneDto;
import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Exception.DroneException;
import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.Medication;
import DroneDeliveryService.Ajua.Repository.DroneRepository;
import DroneDeliveryService.Ajua.Repository.MedicationRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DroneServiceImpl  implements DroneService {
    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MedicationRepository medicationRepository;

    private static Logger logger = LoggerFactory.getLogger(DroneController.class);

    @Override
    @Transactional
    public RegisterDroneDto registerDrone(RegisterDroneDto registerDroneDto) {
        if (registerDroneDto == null) {
            throw new DroneException("Drone registration DTO cannot be null");
        }

        // Validate weight limit
        double weightLimit = registerDroneDto.getWeightLimit();
        if (weightLimit <= 0) {
            throw new DroneException("Weight limit must be greater than zero");
        }

        // Determine if the weight limit is valid for the selected drone model
        DroneModel model = registerDroneDto.getModel();
        if (!isWeightLimitValidForModel(model, weightLimit)) {
            throw new DroneException("Weight limit exceeds the allowed limit for the selected model");
        }

        // Check if serial number is unique
        if (droneRepository.findBySerialNumber(registerDroneDto.getSerialNumber()).isPresent()) {
            throw new DroneException("A drone with this serial number already exists");
        }

        // Mapping DTO to entity
        Drone newDrone = modelMapper.map(registerDroneDto, Drone.class);

        // Save the drone entity
        Drone savedDrone = droneRepository.save(newDrone);

        // Mapping back to DTO and returning
        return modelMapper.map(savedDrone, RegisterDroneDto.class);
    }

    @Override
    @Transactional
    public void loadMedication(UUID serialNumber, List<Long> medicationIds, Double totalWeight) {
        // 1. Fetch the drone from the repository
        Drone drone = droneRepository.findById(serialNumber)
                .orElseThrow(() -> new IllegalArgumentException("Drone not found with serial number: " + serialNumber));

        // 2. Check battery capacity
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone battery capacity is below 25%. Cannot load medications.");
        }

        // 2. Validate weight limit for the drone model
        if (!isWeightLimitValidForModel(drone.getModel(), drone.getWeightLimit())) {
            throw new IllegalArgumentException("Total weight exceeds the weight limit for the drone model.");
        }

        // 2. Fetch all medications by their IDs
        List<Medication> medications = medicationRepository.findAllById(medicationIds);

        // 3. Associate medications with the drone
        for (Medication medication : medications) {
            medication.setDrone(drone);
        }

        // 4. Save the updated medications (with drone association)
        medicationRepository.saveAll(medications);

        drone.setState(DroneState.LOADED);
        droneRepository.save(drone);
    }

    // public function for checking weight of drone according to the model
    public boolean isWeightLimitValidForModel(DroneModel model, double weightLimit) {
        // Assuming that different models have different weight limits.
        // These limits should be defined somewhere (e.g., an enum or a properties file).
        switch (model) {
            case Lightweight:
                return weightLimit <= 200;
            case Middleweight:
                return weightLimit <= 300;
            case Cruiserweight:
                return weightLimit <= 400;
            case Heavyweight:
                return weightLimit <= 500;
            default:
                return false;
        }
    }



    @Override
    public List<Medication> getLoadedMedications(UUID serialNumber) {
        Optional<Drone> droneOptional = droneRepository.findBySerialNumber(serialNumber);
        Drone drone = droneOptional.orElseThrow(()->
                new DroneException("Drone not found with that serial Number: " + serialNumber));

        return  drone.getMedications();
    }


    @Override
    public List<AvailableDronesDto> getAvailableDrones(DroneState state) {
        // first we have to fetch the drone by state and ensure battery level is >= 25%
        List<Drone> drones = droneRepository.findByStateAndBatteryLevel(state, 25);

        // check if drones list is empty
        if (drones.isEmpty()){
            throw  new DroneException("No drones found");
        }

        return drones.stream()
                .map(this::mapToAVailableDronesDto)
                .collect(Collectors.toList());
    }

    private AvailableDronesDto mapToAVailableDronesDto(Drone drone) {
        return AvailableDronesDto.builder()
                .serialNumber(drone.getSerialNumber())
                .model(drone.getModel().toString())
                .weightLimit(drone.getWeightLimit())
                .batteryCapacity(drone.getBatteryCapacity())
                .state(drone.getState().toString())
                .build();
    }


    @Override
    public Integer checkDroneBatteryLevel(UUID serialNumber) {
        //first fetch the drone from repository
        Optional<Drone> droneOptional = droneRepository.findBySerialNumber(serialNumber);
        Drone drone = droneOptional.orElseThrow(()->
                new DroneException("Drone not found with that serial Number: " + serialNumber));

        // then retrieve the battery level
        Integer batteryLevel = drone.getBatteryCapacity();

        //Return the battery level
        return batteryLevel;
    }

}
