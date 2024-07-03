package DroneDeliveryService.Ajua.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Builder
public class DroneBatteryAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID serialNumber;

    private Integer batteryCapacity;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serialNumber", referencedColumnName = "serialNumber", insertable = false, updatable = false )
    private Drone drone;


    public DroneBatteryAudit() {
    }

    public DroneBatteryAudit(Long id, UUID serialNumber, Integer batteryCapacity, LocalDateTime timestamp, Drone drone) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.batteryCapacity = batteryCapacity;
        this.timestamp = timestamp;
        this.drone = drone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
