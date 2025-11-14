package co.edu.umanizales.maviation_api.model;

import jdk.jshell.Snippet;
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
    private  Aircraft aircraft; // Composition: armament belongs to aircraft
    private Mission mission; // Composition: armament used in mission
    private Snippet.Status status; // READY, DEPLOYED, MAINTENANCE, DEPLETED
}
