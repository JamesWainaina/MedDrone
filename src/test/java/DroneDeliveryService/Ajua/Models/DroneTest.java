package DroneDeliveryService.Ajua.Models;

import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    private Drone drone;

    @BeforeEach
    public void setUp(){
        List<Medication> medications = Arrays.asList(
          new Medication(1L, "Med1", 10.0, "CODE1", null, null),
          new Medication(2L, "Medd2", 15.0, "CODE2", null, null)
        );

        List<DroneBatteryAudit> batteryAudits = Arrays.asList(
                new DroneBatteryAudit(1L, UUID.randomUUID(), 80, null, null),
                new DroneBatteryAudit(2L, UUID.randomUUID(), 50, null, null)
        );

        drone = Drone.builder()
                .serialNumber(UUID.randomUUID())
                .model(DroneModel.Heavyweight)
                .weightLimit(450.0)
                .batteryCapacity(90)
                .state(DroneState.LOADED)
                .medications(medications)
                .batteryAudits(batteryAudits)
                .build();
    }

    @Test
    void getSerialNumber() {
        assertEquals(drone.getSerialNumber(), drone.getSerialNumber());
    }

    @Test
    void setSerialNumber() {
        UUID newSerialNumber = UUID.randomUUID();
        drone.setSerialNumber(newSerialNumber);
        assertEquals(newSerialNumber, drone.getSerialNumber());
    }

    @Test
    void setWeightLimit() {
        Double newWeightLimit = 500.0;
        drone.setWeightLimit(newWeightLimit);
        assertEquals(newWeightLimit, drone.getWeightLimit());
    }

    @Test
    void getModel() {
        assertEquals(DroneModel.Heavyweight, drone.getModel());
    }

    @Test
    void setModel() {
        DroneModel newModel = DroneModel.Middleweight;
        drone.setModel(newModel);
        assertEquals(newModel, drone.getModel());
    }

    @Test
    void getWeightLimit() {
        assertEquals(450.0, drone.getWeightLimit(), 0);
    }

    @Test
    void getBatteryCapacity() {
        assertEquals(90, drone.getBatteryCapacity());
    }

    @Test
    void setBatteryCapacity() {
        Integer newBatteryCapacity = 97;
        drone.setBatteryCapacity(newBatteryCapacity);
        assertEquals(newBatteryCapacity, drone.getBatteryCapacity());
    }

    @Test
    void getState() {
        assertEquals(DroneState.LOADED, drone.getState());
    }

    @Test
    void setState() {
        DroneState newState = DroneState.DELIVERING;
        drone.setState(newState);
        assertEquals(newState, drone.getState());
    }

    @Test
    void getMedications() {
        assertEquals(2, drone.getMedications().size());
    }

    @Test
    void setMedications() {
        List<Medication> newMedications = Arrays.asList(
                new Medication(3L, "Med3", 20.0, "CODE3", null, null),
                new Medication(4L, "Med4", 25.0, "CODE4", null, null)
        );
        drone.setMedications(newMedications);
        assertEquals(2, drone.getMedications().size());
    }

    @Test
    void getBatteryAudits() {
        assertEquals(2, drone.getBatteryAudits().size());
    }

    @Test
    void setBatteryAudits() {
        List<DroneBatteryAudit> newBatteryAudits = Arrays.asList(
                new DroneBatteryAudit(3L, UUID.randomUUID(), 70, null, null),
                new DroneBatteryAudit(4L, UUID.randomUUID(), 65, null, null)
        );
        drone.setBatteryAudits(newBatteryAudits);
        assertEquals(2, drone.getBatteryAudits().size());
    }

    @Test
    void testToString() {
        String expectedToString = "Drone{serialNumber='" + drone.getSerialNumber() +
                "', model=" + drone.getModel() +
                ", weightLimit=" + drone.getWeightLimit() +
                ", batteryCapacity=" + drone.getBatteryCapacity() +
                ", state=" + drone.getState() +
                ", medications=" + drone.getMedications() +
                ", batteryAudits=" + drone.getBatteryAudits() +
                '}';
        assertEquals(expectedToString, drone.toString());
    }

    @Test
    void builder() {
        assertNotNull(drone);
    }
}