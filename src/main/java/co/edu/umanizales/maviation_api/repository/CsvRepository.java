package co.edu.umanizales.maviation_api.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvRepository<T> {
    protected String filePath;
    
    public CsvRepository(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }
    
    protected void initializeFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                writeHeader();
            } catch (IOException e) {
                throw new RuntimeException("Error initializing CSV file: " + filePath, e);
            }
        }
    }
    
    protected abstract void writeHeader() throws IOException;
    
    protected abstract T parseLine(String line);
    
    protected abstract String toCSVLine(T entity);
    
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    entities.add(parseLine(line));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file: " + filePath, e);
        }
        return entities;
    }
    
    public void save(T entity) {
        List<T> entities = findAll();
        entities.add(entity);
        saveAll(entities);
    }
    
    public void saveAll(List<T> entities) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writeHeaderToWriter(writer);
            for (T entity : entities) {
                writer.write(toCSVLine(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file: " + filePath, e);
        }
    }
    
    protected abstract void writeHeaderToWriter(BufferedWriter writer) throws IOException;
    
    public void deleteById(String id) {
        List<T> entities = findAll();
        entities.removeIf(entity -> getEntityId(entity).equals(id));
        saveAll(entities);
    }
    
    protected abstract String getEntityId(T entity);
}
