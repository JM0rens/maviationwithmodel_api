package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.BaseCommander;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import co.edu.umanizales.maviation_api.model.enums.AirBaseStatus;
import java.util.List;

/**
 * Represents an air base (BaseAerea)
 * Aggregates squadrons and vehicles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirBase {
    private String id;
    private String name;
    private String location;
    private String country;
    private BaseCommander baseCommander;
    private List<String> squadronIds; // Aggregation: squadrons at this base
    private List<String> vehicleIds; // Aggregation: all vehicles at this base
    private Integer runwayCount;
    private Integer hangarCapacity;
    private AirBaseStatus status; // OPERATIONAL, MAINTENANCE, STANDBY, CLOSED
    private double latitude;
    private double longitude;
}
