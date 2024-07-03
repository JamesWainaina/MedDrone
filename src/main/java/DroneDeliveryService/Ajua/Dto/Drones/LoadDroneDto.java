package DroneDeliveryService.Ajua.Dto.Drones;

import lombok.*;

import java.util.UUID;


@Builder
public class LoadDroneDto {
    private UUID serialNumber;
    private String medicationName;
    private String code;
    private double  weight;

    public LoadDroneDto() {
    }

    public LoadDroneDto(UUID serialNumber, String medicationName, String code, double weight) {
        this.serialNumber = serialNumber;
        this.medicationName = medicationName;
        this.code = code;
        this.weight = weight;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "LoadDroneDto{" +
                "serialNumber=" + serialNumber +
                ", medicationName='" + medicationName + '\'' +
                ", code='" + code + '\'' +
                ", weight=" + weight +
                '}';
    }
}
