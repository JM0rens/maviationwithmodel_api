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
    private Pilot currentPilot;
    private Mission currentMission;
    private Squadron squadron;
    private List<Maintenance> maintenanceHistory;
    private List<Armament> armaments;
    // Backward-compatibility fields used by repositories/services
    private Integer hangarNumber; // optional
    private Pilot pilot; // optional mirror of currentPilot
    private String mission; // optional mirror of currentMission
    
    public Aircraft(String id, String designation, String serialNumber, AircraftType type, 
                   String model, String manufacturer, int maxSpeed, int range, 
                   int serviceYear, AircraftStatus status) {
        super(id, designation, status.toString(), serialNumber);
        this.type = type;
        this.model = model;
        this.manufacturer = manufacturer;
        this.maxSpeed = maxSpeed;
        this.range = range;
        this.serviceYear = serviceYear;
    }

    // Overloaded constructor for CSV repository compatibility
    public Aircraft(String id, String name, String status, String type,
                    String model, Integer hangarNumber, Pilot pilot, String mission) {
        super(id, name, status, null);
        try {
            this.type = type != null ? AircraftType.valueOf(type) : null;
        } catch (IllegalArgumentException ex) {
            this.type = null;
        }
        this.model = model;
        this.hangarNumber = hangarNumber;
        this.pilot = pilot;
        this.mission = mission;
    }

    // Compatibility accessors for repository code expecting getName()
    public String getName() { return getDesignation(); }
    public void setName(String name) { setDesignation(name); }
}
