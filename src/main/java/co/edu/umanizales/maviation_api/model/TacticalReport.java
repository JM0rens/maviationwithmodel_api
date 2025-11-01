package co.edu.umanizales.maviation_api.model;

import java.time.LocalDateTime;

/**
 * Record representing tactical reports (ReporteTactico)
 * Aggregated by missions
 */
public record TacticalReport(
    String id,
    String missionId,
    String reporterId, // Pilot or commander who created the report
    LocalDateTime reportDate,
    String reportType, // SITREP, DEBRIEF, COMBAT, INTELLIGENCE, WEATHER
    String title,
    String content,
    String priority, // LOW, MEDIUM, HIGH, URGENT
    String classification, // UNCLASSIFIED, CONFIDENTIAL, SECRET, TOP_SECRET
    String targetDetails,
    String enemyActivity,
    String weatherConditions,
    String recommendations
) {
}
