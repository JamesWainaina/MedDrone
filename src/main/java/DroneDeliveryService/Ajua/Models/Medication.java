package DroneDeliveryService.Ajua.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Arrays;


@Builder
@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medication_id")
    private Long medicationId;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Medication can only contain uppercase letters, numbers, and '_.")
    @Column(name = "medication_name", nullable = false, unique = true)
    private String medicationName;


    @Column(nullable = false)
    private Double weight;

    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Medication code can only contain uppercase letters, numbers, and '_.")
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "medication_Picture", columnDefinition = "BINARY")
    private byte[] medicationPicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serialNumber", referencedColumnName = "serialNumber")
    @JsonBackReference
    private Drone drone;

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + medicationId+
                ", name='" + medicationName + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
                ", medicationPicture=" + Arrays.toString(medicationPicture) +
                '}';
    }

    public Medication() {
    }

    public Medication(Long medicationId, String medicationName, Double weight, String code, byte[] medicationPicture, Drone drone) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.weight = weight;
        this.code = code;
        this.medicationPicture = medicationPicture;
        this.drone = drone;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Medication can only contain uppercase letters, numbers, and '_.") String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(@Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Medication can only contain uppercase letters, numbers, and '_.") String medicationName) {
        this.medicationName = medicationName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public @Pattern(regexp = "^[A-Z0-9_]+$", message = "Medication code can only contain uppercase letters, numbers, and '_.") String getCode() {
        return code;
    }

    public void setCode(@Pattern(regexp = "^[A-Z0-9_]+$", message = "Medication code can only contain uppercase letters, numbers, and '_.") String code) {
        this.code = code;
    }

    public byte[] getMedicationPicture() {
        return medicationPicture;
    }

    public void setMedicationPicture(byte[] medicationPicture) {
        this.medicationPicture = medicationPicture;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}
