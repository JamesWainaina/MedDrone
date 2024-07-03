package DroneDeliveryService.Ajua.Service;

import DroneDeliveryService.Ajua.Dto.Medication.CreateMedicationDto;
import DroneDeliveryService.Ajua.Models.Medication;
import DroneDeliveryService.Ajua.Repository.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MedicationServiceImplTest {

    private MedicationServiceImpl medicationService;
    private MedicationRepository medicationRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        medicationRepository = mock(MedicationRepository.class);
        modelMapper = mock(ModelMapper.class);
        medicationService = new MedicationServiceImpl(medicationRepository, modelMapper);
    }

    @Test
    void createMedication() {
        CreateMedicationDto requestDto = new CreateMedicationDto();
        requestDto.setMedicationName("Medication A");

        Medication medication = new Medication();
        medication.setMedicationId(1L);
        medication.setMedicationName("Medication A");

        when(medicationRepository.findByMedicationName("Medication A")).thenReturn(Optional.empty());
        when(modelMapper.map(requestDto, Medication.class)).thenReturn(medication);
        when(medicationRepository.save(any(Medication.class))).thenReturn(medication);
        when(modelMapper.map(medication, CreateMedicationDto.class)).thenReturn(requestDto);

        CreateMedicationDto result = medicationService.createMedication(requestDto);


        assertNotNull(result);
        assertEquals("Medication A", result.getMedicationName());
        verify(medicationRepository, times(1)).findByMedicationName("Medication A");
        verify(medicationRepository, times(1)).save(any(Medication.class));
    }
}