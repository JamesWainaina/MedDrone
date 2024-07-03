package DroneDeliveryService.Ajua.Dto.Drones;


import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public class RegisterDroneDto {

    @Column(length = 100)
    private UUID serialNumber;
    @NotNull
    private DroneModel model;
    @NotNull
    private Double weightLimit;
    @NotNull
    @Min(value = 0, message = "Battery capacity must be between 0 and 100 percent")
    @Max(value = 100, message = "Battery capacity must be between 0 and 100 percent")
    private Integer batteryCapacity;
    @NotNull
    private DroneState state;

    public RegisterDroneDto() {
    }

    public RegisterDroneDto(UUID serialNumber,
                            DroneModel model, Double weightLimit,
                            Integer batteryCapacity, DroneState state) {
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

    public @NotNull DroneModel getModel() {
        return model;
    }

    public void setModel(@NotNull DroneModel model) {
        this.model = model;
    }

    public @NotNull Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(@NotNull Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public @NotNull @Min(value = 0, message = "Battery capacity must be between 0 and 100 percent") @Max(value = 100, message = "Battery capacity must be between 0 and 100 percent") Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(@NotNull @Min(value = 0, message = "Battery capacity must be between 0 and 100 percent") @Max(value = 100, message = "Battery capacity must be between 0 and 100 percent") Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public @NotNull DroneState getState() {
        return state;
    }

    public void setState(@NotNull DroneState state) {
        this.state = state;
    }

}
