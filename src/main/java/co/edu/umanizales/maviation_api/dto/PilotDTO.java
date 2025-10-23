package co.edu.umanizales.maviation_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PilotDTO {
    private String id;
    private String name;
    private String status;
    private String rank;
    private Integer flightHours;
    private String specialization;
}
