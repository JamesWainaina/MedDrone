package DroneDeliveryService.Ajua.Contollers;

import DroneDeliveryService.Ajua.Dto.Drones.AvailableDronesDto;
import DroneDeliveryService.Ajua.Dto.Drones.RegisterDroneDto;
import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import DroneDeliveryService.Ajua.Models.Medication;
import DroneDeliveryService.Ajua.Service.DroneService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DroneController.class)
@ExtendWith(MockitoExtension.class)
class DroneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DroneService droneService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void registerDrone() throws Exception {

        RegisterDroneDto dto = new RegisterDroneDto();
        dto.setSerialNumber(UUID.randomUUID());
        dto.setModel(DroneModel.Lightweight);
        dto.setWeightLimit(150.0);
        dto.setBatteryCapacity(100);
        dto.setState(DroneState.IDLE);

        // Mock behavior of DroneService
        when(droneService.registerDrone(any(RegisterDroneDto.class))).thenReturn(dto);

        // Perform POST request with JSON content
        mockMvc.perform(post("/api/drones/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"serialNumber\":\"" + dto.getSerialNumber() +
                                "\",\"model\":\"Lightweight\",\"weightLimit\":150.0," +
                                "\"batteryCapacity\":100,\"state\":\"IDLE\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void loadMedication() throws Exception {
        DroneController.LoadMedicationRequest request = new DroneController.LoadMedicationRequest();
        request.setMedicationIds(List.of(1L, 2L));
        request.setTotalWeight(100.0);

        UUID serialNumber = UUID.randomUUID();

        mockMvc.perform(post("/api/drones/{serialNumber}/load", serialNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Medications loaded successfully."));
    }

    @Test
    void getLoadedMedications() throws Exception {
        UUID serialNumber = UUID.randomUUID();
        List<Medication> medications = Collections.emptyList();

        when(droneService.getLoadedMedications(serialNumber)).thenReturn(medications);

        mockMvc.perform(get("/api/drones/{serialNumber}/medications", serialNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getAvailableDrones() throws Exception {
        AvailableDronesDto availableDronesDto1 = new AvailableDronesDto();
        availableDronesDto1.setSerialNumber(UUID.randomUUID());
        availableDronesDto1.setModel(DroneModel.Lightweight.toString());
        availableDronesDto1.setWeightLimit(200.0);
        availableDronesDto1.setBatteryCapacity(50);
        availableDronesDto1.setState(DroneState.IDLE.toString());

        AvailableDronesDto availableDronesDto2 = new AvailableDronesDto();
        availableDronesDto2.setSerialNumber(UUID.randomUUID());
        availableDronesDto2.setModel(DroneModel.Middleweight.toString());
        availableDronesDto2.setWeightLimit(300.0);
        availableDronesDto2.setBatteryCapacity(70);
        availableDronesDto2.setState(DroneState.IDLE.toString());

        List<AvailableDronesDto> availableDrones = List.of(availableDronesDto1, availableDronesDto2);

        when(droneService.getAvailableDrones(DroneState.IDLE)).thenReturn(availableDrones);

        mockMvc.perform(get("/api/drones/available-drones?state=IDLE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].serialNumber")
                        .value(availableDronesDto1.getSerialNumber().toString()))
                .andExpect(jsonPath("$[1].serialNumber").
                        value(availableDronesDto2.getSerialNumber().toString()));
    }

    @Test
    void checkDroneBatteryLevel() throws Exception {
        UUID serialNumber = UUID.randomUUID();
        Integer batteryLevel = 75;

        when(droneService.checkDroneBatteryLevel(serialNumber)).thenReturn(batteryLevel);

        mockMvc.perform(get("/api/drones/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.batteryLevel").value(batteryLevel));
    }

}