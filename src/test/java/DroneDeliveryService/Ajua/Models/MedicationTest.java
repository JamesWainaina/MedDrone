package DroneDeliveryService.Ajua.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {

    private Medication medication;

    @BeforeEach
    void setUp() {
        Drone drone = new Drone();
        medication = Medication.builder()
                .medicationId(1L)
                .medicationName("Medication1")
                .weight(15.0)
                .code("ABC123")
                .medicationPicture(new byte[]{})
                .drone(drone)
                .build();
    }

    @Test
    void testToString() {
        String expectedString = "Medication{id=1, name='Medication1', weight=15.0, code='ABC123', medicationPicture=[]}";
        assertEquals(expectedString, medication.toString());
    }

    @Test
    void getMedicationId() {
        assertEquals(1L, medication.getMedicationId());
    }

    @Test
    void setMedicationId() {
        medication.setMedicationId(2L);
        assertEquals(2L, medication.getMedicationId());
    }

    @Test
    void getMedicationName() {
        assertEquals("Medication1", medication.getMedicationName());
    }

    @Test
    void setMedicationName() {
        medication.setMedicationName("Medication2");
        assertEquals("Medication2", medication.getMedicationName());
    }

    @Test
    void getWeight() {
        assertEquals(15.0, medication.getWeight());
    }

    @Test
    void setWeight() {
        medication.setWeight(20.0);
        assertEquals(20.0, medication.getWeight());
    }

    @Test
    void getCode() {
        assertEquals("ABC123", medication.getCode());
    }

    @Test
    void setCode() {
        medication.setCode("XYZ456");
        assertEquals("XYZ456", medication.getCode());
    }

    @Test
    void getMedicationPicture() {
        assertArrayEquals(new byte[]{}, medication.getMedicationPicture());
    }

    @Test
    void setMedicationPicture() {
        byte[] newPicture = {1, 2, 3};
        medication.setMedicationPicture(newPicture);
        assertArrayEquals(newPicture, medication.getMedicationPicture());
    }

    @Test
    void getDrone() {
        assertNotNull(medication.getDrone());
    }

    @Test
    void setDrone() {
        Drone newDrone = new Drone();
        medication.setDrone(newDrone);
        assertEquals(newDrone, medication.getDrone());
    }

    @Test
    void builder() {
        assertNotNull(medication);
    }
}