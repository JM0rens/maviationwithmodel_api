package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.dto.AircraftDTO;
import co.edu.umanizales.maviation_api.model.Aircraft;
import co.edu.umanizales.maviation_api.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AircraftService {
    
    @Autowired
    private AircraftRepository aircraftRepository;
    
    public List<AircraftDTO> getAllAircraft() {
        return aircraftRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    public Optional<AircraftDTO> getAircraftById(String id) {
        return aircraftRepository.findAll().stream()
            .filter(a -> a.getId().equals(id))
            .findFirst()
            .map(this::toDTO);
    }
    
    public AircraftDTO createAircraft(AircraftDTO dto) {
        validateHangarNumber(dto.getHangarNumber(), null);
        
        Aircraft aircraft = new Aircraft();
        aircraft.setId(UUID.randomUUID().toString());
        aircraft.setName(dto.getName());
        aircraft.setStatus(dto.getStatus() != null ? dto.getStatus() : "ACTIVE");
        aircraft.setType(dto.getType());
        aircraft.setModel(dto.getModel());
        aircraft.setHangarNumber(dto.getHangarNumber());
        aircraft.setPilotId(null);
        aircraft.setMission(null);
        
        aircraftRepository.save(aircraft);
        return toDTO(aircraft);
    }
    
    public AircraftDTO updateAircraft(String id, AircraftDTO dto) {
        List<Aircraft> aircraft = aircraftRepository.findAll();
        Optional<Aircraft> existingAircraft = aircraft.stream()
            .filter(a -> a.getId().equals(id))
            .findFirst();
        
        if (existingAircraft.isEmpty()) {
            throw new RuntimeException("Aircraft not found with id: " + id);
        }
        
        Aircraft aircraftToUpdate = existingAircraft.get();
        
        if (dto.getHangarNumber() != null && !dto.getHangarNumber().equals(aircraftToUpdate.getHangarNumber())) {
            validateHangarNumber(dto.getHangarNumber(), id);
        }
        
        aircraftToUpdate.setName(dto.getName());
        aircraftToUpdate.setStatus(dto.getStatus());
        aircraftToUpdate.setType(dto.getType());
        aircraftToUpdate.setModel(dto.getModel());
        aircraftToUpdate.setHangarNumber(dto.getHangarNumber());
        
        aircraftRepository.saveAll(aircraft);
        return toDTO(aircraftToUpdate);
    }
    
    public void deleteAircraft(String id) {
        aircraftRepository.deleteById(id);
    }
    
    public List<AircraftDTO> getAvailableAircraft() {
        return aircraftRepository.findAll().stream()
            .filter(a -> "ACTIVE".equals(a.getStatus()) && a.getPilotId() == null)
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    private void validateHangarNumber(Integer hangarNumber, String excludeAircraftId) {
        if (hangarNumber == null || hangarNumber < 1 || hangarNumber > 100) {
            throw new RuntimeException("Hangar number must be between 1 and 100");
        }
        
        boolean hangarOccupied = aircraftRepository.findAll().stream()
            .filter(a -> excludeAircraftId == null || !a.getId().equals(excludeAircraftId))
            .anyMatch(a -> hangarNumber.equals(a.getHangarNumber()));
        
        if (hangarOccupied) {
            throw new RuntimeException("Hangar " + hangarNumber + " is already occupied");
        }
    }
    
    private AircraftDTO toDTO(Aircraft aircraft) {
        return new AircraftDTO(
            aircraft.getId(),
            aircraft.getName(),
            aircraft.getStatus(),
            aircraft.getType(),
            aircraft.getModel(),
            aircraft.getHangarNumber(),
            aircraft.getPilotId(),
            aircraft.getMission()
        );
    }
}
