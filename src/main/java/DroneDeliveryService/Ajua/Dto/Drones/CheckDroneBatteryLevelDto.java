package DroneDeliveryService.Ajua.Dto.Drones;


import lombok.*;

import java.util.UUID;


@Builder
public class CheckDroneBatteryLevelDto {

    private UUID serialNumber;
    private Integer batteryLevel;

    public CheckDroneBatteryLevelDto() {
    }

    public CheckDroneBatteryLevelDto(UUID serialNumber, Integer batteryLevel) {
        this.serialNumber = serialNumber;
        this.batteryLevel = batteryLevel;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}
