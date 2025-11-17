package co.edu.umanizales.maviation_api.repository;

import co.edu.umanizales.maviation_api.model.Deployment;
import co.edu.umanizales.maviation_api.model.enums.DeploymentStatus;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Repository
public class DeploymentRepository extends CsvRepository<Deployment> {
    
    public DeploymentRepository() {
        super("data/deployments.csv");
    }
    
    @Override
    protected void writeHeader() throws IOException {
        try (BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath))) {
            writeHeaderToWriter(writer);
        }
    }
    
    @Override
    protected void writeHeaderToWriter(BufferedWriter writer) throws IOException {
        writer.write("id,aircraftId,pilotId,mission,deploymentDate,status");
        writer.newLine();
    }
    
    @Override
    protected Deployment parseLine(String line) {
        String[] parts = line.split(",", -1);
        LocalDateTime date = parts[4].isEmpty() ? null : LocalDateTime.parse(parts[4]);
        DeploymentStatus status = parts[5].isEmpty() ? null : DeploymentStatus.valueOf(parts[5]);
        return new Deployment(
            parts[0],
            null, // aircraft to be resolved by service from aircraftId
            null, // pilot to be resolved by service from pilotId
            null, // mission to be resolved by service from mission identifier
            date,
            status
        );
    }
    
    @Override
    protected String toCSVLine(Deployment deployment) {
        return String.format("%s,%s,%s,%s,%s,%s",
            deployment.getId(),
            deployment.getAircraft() != null && deployment.getAircraft().getId() != null ? deployment.getAircraft().getId() : "",
            deployment.getPilot() != null && deployment.getPilot().getId() != null ? deployment.getPilot().getId() : "",
            deployment.getMission() != null && deployment.getMission().getId() != null ? deployment.getMission().getId() : "",
            deployment.getDeploymentDate() != null ? deployment.getDeploymentDate().toString() : "",
            deployment.getStatus() != null ? deployment.getStatus().name() : ""
        );
    }
    
    @Override
    protected String getEntityId(Deployment entity) {
        return entity.getId();
    }
}
