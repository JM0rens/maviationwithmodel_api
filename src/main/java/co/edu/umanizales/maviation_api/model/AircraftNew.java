package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a military aircraft (Aeronave)
 * Inherits from MilitaryVehicle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AircraftNew extends MilitaryVehicle {
    private String aircraftType; // FIGHTER, BOMBER, TRANSPORT, RECONNAISSANCE, HELICOPTER
    private String model;
    private String manufacturer;
    private Integer maxSpeed; // in km/h
    private Integer range; // in km
    private Integer serviceYear;
    private String currentPilotId;
    private String currentMissionId;
    
    public AircraftNew(String id, String designation, String status, String serialNumber,
                       String aircraftType, String model, String manufacturer, 
                       Integer maxSpeed, Integer range, Integer serviceYear,
                       String currentPilotId, String currentMissionId) {
        super(id, designation, status, serialNumber);
        this.aircraftType = aircraftType;
        this.model = model;
        this.manufacturer = manufacturer;
        this.maxSpeed = maxSpeed;
        this.range = range;
        this.serviceYear = serviceYear;
        this.currentPilotId = currentPilotId;
        this.currentMissionId = currentMissionId;
    }
}
