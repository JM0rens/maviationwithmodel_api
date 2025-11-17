package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.SquadronType;
import co.edu.umanizales.maviation_api.model.enums.SquadronStatus;
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
    private SquadronType squadronType; // FIGHTER, BOMBER, TRANSPORT, MIXED
    private Pilot commander;
    private List<Pilot> pilots; // Composition: pilots belong to squadron
    private List<Aircraft> aircraft; // Composition: aircraft assigned to squadron
    private AirBase base;
    private SquadronStatus status; // ACTIVE, STANDBY, DEPLOYED, INACTIVE
    private Integer establishedYear;
}
