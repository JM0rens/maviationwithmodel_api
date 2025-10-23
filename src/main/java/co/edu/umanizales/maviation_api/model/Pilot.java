package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pilot extends Team {
    private String rank; // CAPTAIN, MAJOR, COLONEL, etc.
    private Integer flightHours;
    private String specialization; // FIGHTER, BOMBER, etc.
    
    public Pilot(String id, String name, String status, String rank, Integer flightHours, String specialization) {
        super(id, name, status);
        this.rank = rank;
        this.flightHours = flightHours;
        this.specialization = specialization;
    }
}
