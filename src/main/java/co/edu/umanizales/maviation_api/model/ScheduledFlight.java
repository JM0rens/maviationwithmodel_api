package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.FlightType;
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
    private Aircraft aircraft;
    private Pilot pilot;
    private AirBase departureBase;
    private AirBase destinationBase;
    private FlightType flightType; // TRAINING, PATROL, TRANSPORT, FERRY
    private Integer estimatedDuration; // in minutes
    private String weatherConditions;
    
    public ScheduledFlight(String id, String name, String description, LocalDateTime startDate,
                          LocalDateTime endDate, String status, String flightNumber,
                          Aircraft aircraft, Pilot pilot, AirBase departureBase,
                          AirBase destinationBase, FlightType flightType, Integer estimatedDuration,
                          String weatherConditions) {
        super(id, name, description, startDate, endDate, status);
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.pilot = pilot;
        this.departureBase = departureBase;
        this.destinationBase = destinationBase;
        this.flightType = flightType;
        this.estimatedDuration = estimatedDuration;
        this.weatherConditions = weatherConditions;
    }
}
