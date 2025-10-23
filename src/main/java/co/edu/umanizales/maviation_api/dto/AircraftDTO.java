package co.edu.umanizales.maviation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AircraftDTO {
    private String id;
    private String name;
    private String status;
    private String type;
    private String model;
    private Integer hangarNumber;
    private String pilotId;
    private String mission;
}
