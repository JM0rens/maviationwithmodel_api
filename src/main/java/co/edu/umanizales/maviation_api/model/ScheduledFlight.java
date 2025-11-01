package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a scheduled flight (VueloProgramado)
 * Inherits from MilitaryOperation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ScheduledFlight extends MilitaryOperation {
    private String flightNumber;
    private String aircraftId;
    private String pilotId;
    private String departureBase;
    private String destinationBase;
    private String flightType; // TRAINING, PATROL, TRANSPORT, FERRY
    private Integer estimatedDuration; // in minutes
    private String weatherConditions;
    
    public ScheduledFlight(String id, String name, String description, LocalDateTime startDate,
                          LocalDateTime endDate, String status, String flightNumber,
                          String aircraftId, String pilotId, String departureBase,
                          String destinationBase, String flightType, Integer estimatedDuration,
                          String weatherConditions) {
        super(id, name, description, startDate, endDate, status);
        this.flightNumber = flightNumber;
        this.aircraftId = aircraftId;
        this.pilotId = pilotId;
        this.departureBase = departureBase;
        this.destinationBase = destinationBase;
        this.flightType = flightType;
        this.estimatedDuration = estimatedDuration;
        this.weatherConditions = weatherConditions;
    }
}
