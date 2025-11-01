package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String baseCommanderId;
    private List<String> squadronIds; // Aggregation: squadrons at this base
    private List<String> vehicleIds; // Aggregation: all vehicles at this base
    private Integer runwayCount;
    private Integer hangarCapacity;
    private String status; // OPERATIONAL, MAINTENANCE, STANDBY, CLOSED
    private Double latitude;
    private Double longitude;
}
