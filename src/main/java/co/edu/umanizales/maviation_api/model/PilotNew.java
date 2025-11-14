package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.MilitaryRank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a military pilot (Piloto)
 * Inherits from MilitaryPersonnel
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PilotNew extends MilitaryPersonnel {
    private Integer flightHours;
    private String specialization; // FIGHTER, BOMBER, TRANSPORT, HELICOPTER
    private String licenseNumber;
    private Integer missionsCompleted;
    private String currentAircraftId;
    private String squadronId;
    
    public PilotNew(String id, String name, MilitaryRank rank, String serviceNumber, String status,
                    Integer flightHours, String specialization, String licenseNumber,
                    Integer missionsCompleted, String currentAircraftId, String squadronId) {
        super(id, name, rank, serviceNumber, status);
        this.flightHours = flightHours;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.missionsCompleted = missionsCompleted;
        this.currentAircraftId = currentAircraftId;
        this.squadronId = squadronId;
    }

}
