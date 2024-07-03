package DroneDeliveryService.Ajua.Dto.Drones;

import lombok.Builder;

import java.util.UUID;

@Builder
public class AvailableDronesDto {
    private UUID serialNumber;
    private String model;
    private Double weightLimit;
    private Integer batteryCapacity;
    private String state;

    public AvailableDronesDto() {
    }

    public AvailableDronesDto(UUID serialNumber, String model, Double weightLimit, Integer batteryCapacity, String state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
