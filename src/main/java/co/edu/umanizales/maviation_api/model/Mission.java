package co.edu.umanizales.maviation_api.model;

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
public class
Mission extends MilitaryOperation {
    private String missionType; // COMBAT, RECONNAISSANCE, TRANSPORT, TRAINING, RESCUE
    private String priority; // LOW, MEDIUM, HIGH, CRITICAL
    private String targetLocation;
    private List<String> assignedPilotIds;
    private List<String> assignedAircraftIds;
    private List<String> armamentIds;
    private String commanderId;
    private List<String> tacticalReportIds;
    
    public Mission(String id, String name, String description, LocalDateTime startDate, 
                   LocalDateTime endDate, String status, String missionType, String priority,
                   String targetLocation, List<String> assignedPilotIds, 
                   List<String> assignedAircraftIds, List<String> armamentIds,
                   String commanderId, List<String> tacticalReportIds) {
        super(id, name, description, startDate, endDate, status);
        this.missionType = missionType;
        this.priority = priority;
        this.targetLocation = targetLocation;
        this.assignedPilotIds = assignedPilotIds;
        this.assignedAircraftIds = assignedAircraftIds;
        this.armamentIds = armamentIds;
        this.commanderId = commanderId;
        this.tacticalReportIds = tacticalReportIds;
    }
}
