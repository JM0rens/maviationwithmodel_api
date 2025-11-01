package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents military armament (Armamento)
 * Composition with aircraft and missions
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Armament {
    private String id;
    private String name;
    private String armamentType; // MISSILE, BOMB, CANNON, ROCKET, COUNTERMEASURE
    private String model;
    private String manufacturer;
    private Integer quantity;
    private String caliber;
    private Integer weight; // in kg
    private Integer range; // in meters
    private String aircraftId; // Composition: armament belongs to aircraft
    private String missionId; // Composition: armament used in mission
    private String status; // READY, DEPLOYED, MAINTENANCE, DEPLETED
}
