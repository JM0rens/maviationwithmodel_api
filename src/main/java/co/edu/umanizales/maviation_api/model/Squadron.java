package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a military squadron (Escuadron)
 * Composes pilots and aircraft
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Squadron {
    private String id;
    private String name;
    private String designation; // e.g., "1st Fighter Squadron"
    private String squadronType; // FIGHTER, BOMBER, TRANSPORT, MIXED
    private String commanderId;
    private List<String> pilotIds; // Composition: pilots belong to squadron
    private List<String> aircraftIds; // Composition: aircraft assigned to squadron
    private String baseId;
    private String status; // ACTIVE, STANDBY, DEPLOYED, INACTIVE
    private Integer establishedYear;
}
