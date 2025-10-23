package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.dto.DeploymentDTO;
import co.edu.umanizales.maviation_api.model.Aircraft;
import co.edu.umanizales.maviation_api.model.Deployment;
import co.edu.umanizales.maviation_api.model.Pilot;
import co.edu.umanizales.maviation_api.repository.AircraftRepository;
import co.edu.umanizales.maviation_api.repository.DeploymentRepository;
import co.edu.umanizales.maviation_api.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeploymentService {
    
    @Autowired
    private DeploymentRepository deploymentRepository;
    
    @Autowired
    private AircraftRepository aircraftRepository;
    
    @Autowired
    private PilotRepository pilotRepository;
    
    public Deployment deployAircraft(DeploymentDTO dto) {
        // Validate aircraft exists and is available
        List<Aircraft> aircraft = aircraftRepository.findAll();
        Optional<Aircraft> aircraftOpt = aircraft.stream()
            .filter(a -> a.getId().equals(dto.getAircraftId()))
            .findFirst();
        
        if (aircraftOpt.isEmpty()) {
            throw new RuntimeException("Aircraft not found with id: " + dto.getAircraftId());
        }
        
        Aircraft aircraftToDeploy = aircraftOpt.get();
        if (!"ACTIVE".equals(aircraftToDeploy.getStatus()) || aircraftToDeploy.getPilotId() != null) {
            throw new RuntimeException("Aircraft is not available for deployment");
        }
        
        // Validate pilot exists and is available
        List<Pilot> pilots = pilotRepository.findAll();
        Optional<Pilot> pilotOpt = pilots.stream()
            .filter(p -> p.getId().equals(dto.getPilotId()))
            .findFirst();
        
        if (pilotOpt.isEmpty()) {
            throw new RuntimeException("Pilot not found with id: " + dto.getPilotId());
        }
        
        Pilot pilot = pilotOpt.get();
        if (!"ACTIVE".equals(pilot.getStatus())) {
            throw new RuntimeException("Pilot is not available for deployment");
        }
        
        // Create deployment
        Deployment deployment = new Deployment();
        deployment.setId(UUID.randomUUID().toString());
        deployment.setAircraftId(dto.getAircraftId());
        deployment.setPilotId(dto.getPilotId());
        deployment.setMission(dto.getMission());
        deployment.setDeploymentDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        deployment.setStatus("ACTIVE");
        
        deploymentRepository.save(deployment);
        
        // Update aircraft status
        aircraftToDeploy.setStatus("IN_MISSION");
        aircraftToDeploy.setPilotId(dto.getPilotId());
        aircraftToDeploy.setMission(dto.getMission());
        aircraftRepository.saveAll(aircraft);
        
        // Update pilot status
        pilot.setStatus("IN_MISSION");
        pilotRepository.saveAll(pilots);
        
        return deployment;
    }
    
    public void completeDeployment(String deploymentId) {
        List<Deployment> deployments = deploymentRepository.findAll();
        Optional<Deployment> deploymentOpt = deployments.stream()
            .filter(d -> d.getId().equals(deploymentId))
            .findFirst();
        
        if (deploymentOpt.isEmpty()) {
            throw new RuntimeException("Deployment not found with id: " + deploymentId);
        }
        
        Deployment deployment = deploymentOpt.get();
        deployment.setStatus("COMPLETED");
        deploymentRepository.saveAll(deployments);
        
        // Update aircraft
        List<Aircraft> aircraft = aircraftRepository.findAll();
        aircraft.stream()
            .filter(a -> a.getId().equals(deployment.getAircraftId()))
            .findFirst()
            .ifPresent(a -> {
                a.setStatus("ACTIVE");
                a.setPilotId(null);
                a.setMission(null);
            });
        aircraftRepository.saveAll(aircraft);
        
        // Update pilot
        List<Pilot> pilots = pilotRepository.findAll();
        pilots.stream()
            .filter(p -> p.getId().equals(deployment.getPilotId()))
            .findFirst()
            .ifPresent(p -> p.setStatus("ACTIVE"));
        pilotRepository.saveAll(pilots);
    }
    
    public List<Deployment> getActiveDeployments() {
        return deploymentRepository.findAll().stream()
            .filter(d -> "ACTIVE".equals(d.getStatus()))
            .toList();
    }
    
    public List<Deployment> getAllDeployments() {
        return deploymentRepository.findAll();
    }
}
