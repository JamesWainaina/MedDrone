package DroneDeliveryService.Ajua.Models;

import DroneDeliveryService.Ajua.Enums.DroneModel;
import DroneDeliveryService.Ajua.Enums.DroneState;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "serialNumber", length = 100, updatable = false)
    private UUID serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "model", nullable = false)
    private DroneModel model;

    @Max(value = 500, message = "Weight limit must not exceed 500 grams")
    @Column(name = "weightLimit")
    private Double weightLimit;

    @Min(value = 0, message = "Battery capacity must be between 0 and 100 percent")
    @Max(value = 100, message = "Battery capacity must be between 0 and 100 percent")
    @Column(name = "batteryCapacity")
    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private DroneState state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Medication> medications;

    @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DroneBatteryAudit> batteryAudits;

    // Default constructor
    public Drone() {}

    public Drone(UUID serialNumber, DroneModel model, Double weightLimit, Integer batteryCapacity, DroneState state, List<Medication> medications, List<DroneBatteryAudit> batteryAudits) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.medications = medications;
        this.batteryAudits = batteryAudits;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setWeightLimit(@Max(value = 500, message = "Weight limit must not exceed 500 grams") Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public @Max(value = 500, message = "Weight limit must not exceed 500 grams") Double getWeightLimit() {
        return weightLimit;
    }

    public @Min(value = 0, message = "Battery capacity must be between 0 and 100 percent") @Max(value = 100, message = "Battery capacity must be between 0 and 100 percent") Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(@Min(value = 0, message = "Battery capacity must be between 0 and 100 percent") @Max(value = 100, message = "Battery capacity must be between 0 and 100 percent") Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<DroneBatteryAudit> getBatteryAudits() {
        return batteryAudits;
    }

    public void setBatteryAudits(List<DroneBatteryAudit> batteryAudits) {
        this.batteryAudits = batteryAudits;
    }

    // toString method
    @Override
    public String toString() {
        return "Drone{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                ", medications=" + medications +
                ", batteryAudits=" + batteryAudits +
                '}';
    }
}
