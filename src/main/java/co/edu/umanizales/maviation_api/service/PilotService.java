package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.dto.PilotDTO;
import co.edu.umanizales.maviation_api.model.Pilot;
import co.edu.umanizales.maviation_api.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PilotService {
    
    @Autowired
    private PilotRepository pilotRepository;
    
    public List<PilotDTO> getAllPilots() {
        return pilotRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    public Optional<PilotDTO> getPilotById(String id) {
        return pilotRepository.findAll().stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .map(this::toDTO);
    }
    
    public PilotDTO createPilot(PilotDTO dto) {
        Pilot pilot = new Pilot();
        pilot.setId(UUID.randomUUID().toString());
        pilot.setName(dto.getName());
        pilot.setStatus(dto.getStatus() != null ? dto.getStatus() : "ACTIVE");
        pilot.setRank(dto.getRank());
        pilot.setFlightHours(dto.getFlightHours() != null ? dto.getFlightHours() : 0);
        pilot.setSpecialization(dto.getSpecialization());
        
        pilotRepository.save(pilot);
        return toDTO(pilot);
    }
    
    public PilotDTO updatePilot(String id, PilotDTO dto) {
        List<Pilot> pilots = pilotRepository.findAll();
        Optional<Pilot> existingPilot = pilots.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
        
        if (existingPilot.isEmpty()) {
            throw new RuntimeException("Pilot not found with id: " + id);
        }
        
        Pilot pilotToUpdate = existingPilot.get();
        pilotToUpdate.setName(dto.getName());
        pilotToUpdate.setStatus(dto.getStatus());
        pilotToUpdate.setRank(dto.getRank());
        pilotToUpdate.setFlightHours(dto.getFlightHours());
        pilotToUpdate.setSpecialization(dto.getSpecialization());
        
        pilotRepository.saveAll(pilots);
        return toDTO(pilotToUpdate);
    }
    
    public void deletePilot(String id) {
        pilotRepository.deleteById(id);
    }
    
    public List<PilotDTO> getAvailablePilots() {
        return pilotRepository.findAll().stream()
            .filter(p -> "ACTIVE".equals(p.getStatus()))
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    private PilotDTO toDTO(Pilot pilot) {
        return new PilotDTO(
            pilot.getId(),
            pilot.getName(),
            pilot.getStatus(),
            pilot.getRank(),
            pilot.getFlightHours(),
            pilot.getSpecialization()
        );
    }
}
