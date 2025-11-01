package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract base class for all military vehicles
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class MilitaryVehicle {
    private String id;
    private String designation;
    private String status; // OPERATIONAL, MAINTENANCE, RETIRED
    private String serialNumber;
}
