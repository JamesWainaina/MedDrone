package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Dto.Drones.AvailableDronesDto;
import DroneDeliveryService.Ajua.Dto.Drones.RegisterDroneDto;
import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Models.Drone;
import DroneDeliveryService.Ajua.Models.Medication;
import DroneDeliveryService.Ajua.Repository.DroneRepository;
import DroneDeliveryService.Ajua.Repository.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private DroneServiceImpl droneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerDrone() {
        RegisterDroneDto dto = new RegisterDroneDto();
        UUID serialNumber = UUID.randomUUID();
        dto.setSerialNumber(serialNumber);
        dto.setModel(DroneModel.Lightweight);
        dto.setWeightLimit(150.0);

        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);

        when(droneRepository.findBySerialNumber(serialNumber))
                .thenReturn(Optional.empty());
        when(modelMapper.map(dto, Drone.class)).thenReturn(drone);
        when(droneRepository.save(any(Drone.class))).thenReturn(drone);
        when(modelMapper.map(drone, RegisterDroneDto.class)).thenReturn(dto);

        RegisterDroneDto result = droneService.registerDrone(dto);

        assertNotNull(result);
        assertEquals(serialNumber, result.getSerialNumber());
        verify(droneRepository, times(1)).save(any(Drone.class));
    }

    @Test
    void loadMedication() {
        UUID serialNumber = UUID.randomUUID();
        List<Long> medicationIds = Arrays.asList(1L, 2L, 3L);
        double totalWeight = 100.0;

        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        drone.setBatteryCapacity(30);
        drone.setModel(DroneModel.Lightweight);
        drone.setWeightLimit(200.0);

        Medication medication1 = new Medication();
        Medication medication2 = new Medication();
        Medication medication3 = new Medication();
        List<Medication> medications = Arrays.asList(medication1, medication2, medication3);

        when(droneRepository.findById(serialNumber)).thenReturn(Optional.of(drone));
        when(medicationRepository.findAllById(medicationIds)).thenReturn(medications);

        droneService.loadMedication(serialNumber, medicationIds, totalWeight);

        verify(medicationRepository, times(1)).saveAll(medications);
        verify(droneRepository, times(1)).save(drone);
        assertEquals(DroneState.LOADED, drone.getState());
    }

    @Test
    void isWeightLimitValidForModel() {
        assertTrue(droneService.isWeightLimitValidForModel(DroneModel.Lightweight, 200));
        assertFalse(droneService.isWeightLimitValidForModel(DroneModel.Lightweight, 250));
        assertTrue(droneService.isWeightLimitValidForModel(DroneModel.Middleweight, 300));
        assertFalse(droneService.isWeightLimitValidForModel(DroneModel.Middleweight, 350));
        assertTrue(droneService.isWeightLimitValidForModel(DroneModel.Cruiserweight, 400));
        assertFalse(droneService.isWeightLimitValidForModel(DroneModel.Cruiserweight, 450));
        assertTrue(droneService.isWeightLimitValidForModel(DroneModel.Heavyweight, 500));
        assertFalse(droneService.isWeightLimitValidForModel(DroneModel.Heavyweight, 550));
    }

    @Test
    void getLoadedMedications() {
        UUID serialNumber = UUID.randomUUID();
        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);

        Medication medication1 = new Medication();
        Medication medication2 = new Medication();
        List<Medication> medications = Arrays.asList(medication1, medication2);

        drone.setMedications(medications);

        when(droneRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));

        List<Medication> result = droneService.getLoadedMedications(serialNumber);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(medications, result);
    }

    @Test
    void getAvailableDrones() {
        Drone drone1 = new Drone();
        drone1.setSerialNumber(UUID.randomUUID());
        drone1.setBatteryCapacity(30);
        drone1.setState(DroneState.IDLE);
        drone1.setModel(DroneModel.Lightweight);
        drone1.setWeightLimit(200.0);

        Drone drone2 = new Drone();
        drone2.setSerialNumber(UUID.randomUUID());
        drone2.setBatteryCapacity(35);
        drone2.setState(DroneState.IDLE);
        drone2.setModel(DroneModel.Middleweight);
        drone2.setWeightLimit(300.0);

        List<Drone> drones = Arrays.asList(drone1, drone2);
        when(droneRepository.findByStateAndBatteryLevel(DroneState.IDLE, 25)).thenReturn(drones);

        AvailableDronesDto dto1 = new AvailableDronesDto();
        dto1.setSerialNumber(drone1.getSerialNumber());
        dto1.setModel(drone1.getModel().toString());
        dto1.setBatteryCapacity(drone1.getBatteryCapacity());
        dto1.setState(drone1.getState().toString());
        dto1.setWeightLimit(drone1.getWeightLimit());

        AvailableDronesDto dto2 = new AvailableDronesDto();
        dto2.setSerialNumber(drone2.getSerialNumber());
        dto2.setModel(drone2.getModel().toString());
        dto2.setBatteryCapacity(drone2.getBatteryCapacity());
        dto2.setState(drone2.getState().toString());
        dto2.setWeightLimit(drone2.getWeightLimit());

        List<AvailableDronesDto> expected = Arrays.asList(dto1, dto2);

        List<AvailableDronesDto> result = droneService.getAvailableDrones(DroneState.IDLE);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expected, result);
    }


    @Test
    void checkDroneBatteryLevel() {
        UUID serialNumber = UUID.randomUUID();
        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        drone.setBatteryCapacity(80);

        when(droneRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.of(drone));

        Integer batteryLevel = droneService.checkDroneBatteryLevel(serialNumber);

        assertNotNull(batteryLevel);
        assertEquals(80, batteryLevel);
    }
}