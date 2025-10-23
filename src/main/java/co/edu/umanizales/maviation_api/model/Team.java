package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Team {
    private String id;
    private String name;
    private String status; // ACTIVE, INACTIVE, IN_MISSION
}
