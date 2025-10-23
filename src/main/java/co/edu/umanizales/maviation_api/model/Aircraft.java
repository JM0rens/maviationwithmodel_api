package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Aircraft extends Team {
    private String type; // FIGHTER, BOMBER, TRANSPORT, RECONNAISSANCE
    private String model;
    private Integer hangarNumber; // 1-100
    private String pilotId; // Reference to pilot when deployed
    private String mission; // Current mission if deployed
    
    public Aircraft(String id, String name, String status, String type, String model, Integer hangarNumber, String pilotId, String mission) {
        super(id, name, status);
        this.type = type;
        this.model = model;
        this.hangarNumber = hangarNumber;
        this.pilotId = pilotId;
        this.mission = mission;
    }
}
