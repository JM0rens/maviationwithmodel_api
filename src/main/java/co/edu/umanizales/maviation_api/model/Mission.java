package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.MissionPriority;
import co.edu.umanizales.maviation_api.model.enums.MissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a military mission (Mision)
 * Inherits from MilitaryOperation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Mission extends MilitaryOperation {
    private MissionType missionType;
    private MissionPriority priority;
    private String targetLocation;
    private String targetCoordinates;
    private List<Pilot> assignedPilots;
    private List<Aircraft> assignedAircraft;
    private List<Armament> armaments;
    private Pilot missionCommander;
    private List<TacticalReport> tacticalReports;
    private String objective;
    private String weatherConditions;
    private String intelligenceReport;
    
    public Mission(String id, String name, String description, LocalDateTime startDate, 
                  LocalDateTime endDate, MissionType missionType, MissionPriority priority,
                  String targetLocation, String targetCoordinates, String objective, String status) {
        super(id, name, description, startDate, endDate, status);
        this.missionType = missionType;
        this.priority = priority;
        this.targetLocation = targetLocation;
        this.targetCoordinates = targetCoordinates;
        this.objective = objective;
    }
    
    public void addPilot(Pilot pilot) {
        if (pilot != null && !assignedPilots.contains(pilot)) {
            assignedPilots.add(pilot);
        }
    }
    
    public void addAircraft(Aircraft aircraft) {
        if (aircraft != null && !assignedAircraft.contains(aircraft)) {
            assignedAircraft.add(aircraft);
        }
    }
    
    public void addArmament(Armament armament) {
        if (armament != null && !armaments.contains(armament)) {
            armaments.add(armament);
        }
    }
    
    public void addTacticalReport(TacticalReport report) {
        if (report != null && !tacticalReports.contains(report)) {
            tacticalReports.add(report);
        }
    }
}
