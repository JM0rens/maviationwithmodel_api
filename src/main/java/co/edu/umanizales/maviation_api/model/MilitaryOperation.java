package co.edu.umanizales.maviation_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Abstract base class for all military operations
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class MilitaryOperation {
    private String id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status; // PLANNED, IN_PROGRESS, COMPLETED, ABORTED
}
