package co.edu.umanizales.maviation_api.model;

import co.edu.umanizales.maviation_api.model.enums.MaintenanceStatus;
import co.edu.umanizales.maviation_api.model.enums.MaintenanceType;
import java.time.LocalDateTime;

/**
 * Record representing maintenance operations (Mantenimiento)
 * Associated with aircraft
 */
public record Maintenance(
    String id,
    Aircraft aircraft,
    MaintenanceType maintenanceType, // ROUTINE, PREVENTIVE, CORRECTIVE, EMERGENCY, OVERHAUL
    String description,
    LocalDateTime scheduledDate,
    LocalDateTime completedDate,
    MilitaryPersonnel technician,
    MaintenanceStatus status, // SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    Integer estimatedHours,
    Integer actualHours,
    String partsReplaced,
    Double cost
) {
}
