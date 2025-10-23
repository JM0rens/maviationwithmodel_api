package co.edu.umanizales.maviation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentDTO {
    private String aircraftId;
    private String pilotId;
    private String mission;
}
