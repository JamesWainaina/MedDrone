package DroneDeliveryService.Ajua.Dto.Medication;

import lombok.*;

import java.util.UUID;


@Builder
public class CreateMedicationDto {
    private UUID serialNumber;
    private String medicationId;
    private String medicationName;
    private double weight;
    private String code;
    private String image;

    public CreateMedicationDto() {
    }

    public CreateMedicationDto(UUID serialNumber, String medicationId, String medicationName, double weight, String code, String image) {
        this.serialNumber = serialNumber;
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
