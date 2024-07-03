package DroneDeliveryService.Ajua.Dto.Drones;

import lombok.Builder;

import java.util.Objects;
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


    // overriding test case objects are being compared by their reference rather than their content
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableDronesDto that = (AvailableDronesDto) o;
        return Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(model, that.model) &&
                Objects.equals(weightLimit, that.weightLimit) &&
                Objects.equals(batteryCapacity, that.batteryCapacity) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, model, weightLimit, batteryCapacity, state);
    }

}
