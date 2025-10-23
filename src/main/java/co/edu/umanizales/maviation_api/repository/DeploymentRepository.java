package co.edu.umanizales.maviation_api.repository;

import co.edu.umanizales.maviation_api.model.Deployment;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;

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
        return new Deployment(
            parts[0],
            parts[1],
            parts[2],
            parts[3],
            parts[4],
            parts[5]
        );
    }
    
    @Override
    protected String toCSVLine(Deployment deployment) {
        return String.format("%s,%s,%s,%s,%s,%s",
            deployment.getId(),
            deployment.getAircraftId(),
            deployment.getPilotId(),
            deployment.getMission(),
            deployment.getDeploymentDate(),
            deployment.getStatus()
        );
    }
    
    @Override
    protected String getEntityId(Deployment entity) {
        return entity.getId();
    }
}
