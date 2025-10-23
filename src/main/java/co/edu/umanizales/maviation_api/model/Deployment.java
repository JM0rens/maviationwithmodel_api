package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deployment {
    private String id;
    private String aircraftId;
    private String pilotId;
    private String mission;
    private String deploymentDate;
    private String status; // ACTIVE, COMPLETED, ABORTED
}
