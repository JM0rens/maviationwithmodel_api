package co.edu.umanizales.maviation_api.repository;

import co.edu.umanizales.maviation_api.model.Aircraft;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;

@Repository
public class AircraftRepository extends CsvRepository<Aircraft> {
    
    public AircraftRepository() {
        super("data/aircraft.csv");
    }
    
    @Override
    protected void writeHeader() throws IOException {
        try (BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath))) {
            writeHeaderToWriter(writer);
        }
    }
    
    @Override
    protected void writeHeaderToWriter(BufferedWriter writer) throws IOException {
        writer.write("id,name,status,type,model,hangarNumber,pilotId,mission");
        writer.newLine();
    }
    
    @Override
    protected Aircraft parseLine(String line) {
        String[] parts = line.split(",", -1);
        return new Aircraft(
            parts[0],
            parts[1],
            parts[2],
            parts[3],
            parts[4],
            parts[5].isEmpty() ? null : Integer.parseInt(parts[5]),
            null, // legacy CSV provides only pilotId; object resolution should happen at service layer
            parts[7].isEmpty() ? null : parts[7]
        );
    }
    
    @Override
    protected String toCSVLine(Aircraft aircraft) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
            aircraft.getId(),
            aircraft.getName(),
            aircraft.getStatus(),
            aircraft.getType(),
            aircraft.getModel(),
            aircraft.getHangarNumber() != null ? aircraft.getHangarNumber() : "",
            (aircraft.getPilot() != null && aircraft.getPilot().getId() != null) ? aircraft.getPilot().getId() : "",
            aircraft.getMission() != null ? aircraft.getMission() : ""
        );
    }
    
    @Override
    protected String getEntityId(Aircraft entity) {
        return entity.getId();
    }
}
