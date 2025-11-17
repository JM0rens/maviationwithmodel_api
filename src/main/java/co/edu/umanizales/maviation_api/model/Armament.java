package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.ArmamentStatus;
import co.edu.umanizales.maviation_api.model.enums.ArmamentType;
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
    private ArmamentType armamentType; // MISSILE, BOMB, CANNON, ROCKET, COUNTERMEASURE
    private String model;
    private String manufacturer;
    private Integer quantity;
    private String caliber;
    private Integer weight; // in kg
    private Integer range; // in meters
    private  Aircraft aircraft; // Composition: armament belongs to aircraft
    private Mission mission; // Composition: armament used in mission
    private ArmamentStatus status; // READY, DEPLOYED, MAINTENANCE, DEPLETED
}
