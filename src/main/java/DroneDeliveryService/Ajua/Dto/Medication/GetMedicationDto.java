package DroneDeliveryService.Ajua.Dto.Medication;

import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMedicationDto {

    private String medicationId;
    private String medicationName;
    private Double weight;
    private Byte[] medicationImage;
    private UUID droneSerialNumber;
}
