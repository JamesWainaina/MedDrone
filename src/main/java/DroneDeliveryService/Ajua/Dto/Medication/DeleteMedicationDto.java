package DroneDeliveryService.Ajua.Dto.Medication;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DeleteMedicationDto {
    private String message;
    private boolean delete;
}
