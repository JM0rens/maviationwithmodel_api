package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.AircraftType;
import co.edu.umanizales.maviation_api.model.enums.MilitaryRank;
import co.edu.umanizales.maviation_api.model.enums.PilotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Pilot extends MilitaryPersonnel {
    private MilitaryRank rank;
    private int flightHours;
    private AircraftType specialization;
    private String licenseNumber;
    private int missionsCompleted;
    private Aircraft currentAircraft;
    private Squadron squadron;
    private List<Mission> completedMissions;
    private PilotStatus status;
    
    public Pilot(String id, String name, String serviceNumber, MilitaryRank rank, 
                int flightHours, AircraftType specialization, String licenseNumber) {
        super(id, name, serviceNumber);
        this.rank = rank;
        this.flightHours = flightHours;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.missionsCompleted = 0;
    }
    
    public void addFlightHours(int hours) {
        if (hours > 0) {
            this.flightHours += hours;
        }
    }
    
    public void completeMission() {
        this.missionsCompleted++;
    }
}
