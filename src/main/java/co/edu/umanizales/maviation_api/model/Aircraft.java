package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.AircraftStatus;
import co.edu.umanizales.maviation_api.model.enums.AircraftType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Aircraft extends MilitaryVehicle {
    private AircraftType type;
    private String model;
    private String manufacturer;
    private int maxSpeed; // km/h
    private int range; // km
    private int serviceYear;
    private AircraftStatus status;
    private Pilot currentPilot;
    private Mission currentMission;
    private Squadron squadron;
    private List<Maintenance> maintenanceHistory;
    private List<Armament> armaments;
    
    public Aircraft(String id, String designation, String serialNumber, AircraftType type, 
                   String model, String manufacturer, int maxSpeed, int range, 
                   int serviceYear, AircraftStatus status) {
        super(id, designation, serialNumber);
        this.type = type;
        this.model = model;
        this.manufacturer = manufacturer;
        this.maxSpeed = maxSpeed;
        this.range = range;
        this.serviceYear = serviceYear;
        this.status = status;
    }
}
