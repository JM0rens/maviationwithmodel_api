package co.edu.umanizales.maviation_api.repository;

import co.edu.umanizales.maviation_api.model.Pilot;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;

@Repository
public class PilotRepository extends CsvRepository<Pilot> {
    
    public PilotRepository() {
        super("data/pilots.csv");
    }
    
    @Override
    protected void writeHeader() throws IOException {
        try (BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filePath))) {
            writeHeaderToWriter(writer);
        }
    }
    
    @Override
    protected void writeHeaderToWriter(BufferedWriter writer) throws IOException {
        writer.write("id,name,status,rank,flightHours,specialization");
        writer.newLine();
    }
    
    @Override
    protected Pilot parseLine(String line) {
        String[] parts = line.split(",", -1);
        return new Pilot(
            parts[0],
            parts[1],
            parts[2],
            parts[3],
            parts[4].isEmpty() ? 0 : Integer.parseInt(parts[4]),
            parts[5]
        );
    }
    
    @Override
    protected String toCSVLine(Pilot pilot) {
        return String.format("%s,%s,%s,%s,%s,%s",
            pilot.getId(),
            pilot.getName(),
            pilot.getStatus(),
            pilot.getRank(),
            pilot.getFlightHours(),
            pilot.getSpecialization()
        );
    }
    
    @Override
    protected String getEntityId(Pilot entity) {
        return entity.getId();
    }
}
