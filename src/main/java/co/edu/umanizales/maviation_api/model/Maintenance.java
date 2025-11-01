package co.edu.umanizales.maviation_api.model;

import java.time.LocalDateTime;

/**
 * Record representing maintenance operations (Mantenimiento)
 * Associated with aircraft
 */
public record Maintenance(
    String id,
    String aircraftId,
    String maintenanceType, // ROUTINE, PREVENTIVE, CORRECTIVE, EMERGENCY, OVERHAUL
    String description,
    LocalDateTime scheduledDate,
    LocalDateTime completedDate,
    String technicianId,
    String status, // SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    Integer estimatedHours,
    Integer actualHours,
    String partsReplaced,
    Double cost
) {
}
