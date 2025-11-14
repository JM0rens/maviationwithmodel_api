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
@EqualsAndHashCode(callSuper = true)
public class Pilot extends MilitaryPersonnel {
    private int flightHours;
    private AircraftType specialization;
    private String licenseNumber;
    private int missionsCompleted;
    private Aircraft currentAircraft;
    private Squadron squadron;
    private List<Mission> completedMissions;
    private PilotStatus pilotStatus;
    
    public Pilot(String id, String name, String serviceNumber, MilitaryRank rank, 
                int flightHours, AircraftType specialization, String licenseNumber) {
        super();
        this.setId(id);
        this.setName(name);
        this.setServiceNumber(serviceNumber);
        this.setRank(rank);
        this.flightHours = flightHours;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.missionsCompleted = 0;
    }

    // Overloaded constructor for CSV repository compatibility
    public Pilot(String id, String name, String status, String rank,
                 int flightHours, String specialization) {
        super();
        this.setId(id);
        this.setName(name);
        this.setStatus(status);
        try {
            this.setRank(rank != null && !rank.isBlank() ? MilitaryRank.valueOf(rank) : null);
        } catch (IllegalArgumentException ex) {
            this.setRank(null);
        }
        this.flightHours = flightHours;
        try {
            this.specialization = (specialization != null && !specialization.isBlank())
                    ? AircraftType.valueOf(specialization)
                    : null;
        } catch (IllegalArgumentException ex) {
            this.specialization = null;
        }
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
