package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.MilitaryRank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract base class for all military personnel
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class MilitaryPersonnel {
    private String id;
    private String name;
    private MilitaryRank rank;
    private String serviceNumber;
    private String status; // ACTIVE, ON_LEAVE, RETIRED
}
