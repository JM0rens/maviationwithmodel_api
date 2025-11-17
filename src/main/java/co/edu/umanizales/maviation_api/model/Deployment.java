package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.DeploymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deployment {
    private String id;
    private Aircraft aircraft;
    private Pilot pilot;
    private Mission mission;
    private LocalDateTime deploymentDate;
    private DeploymentStatus status; // ACTIVE, COMPLETED, ABORTED
}
