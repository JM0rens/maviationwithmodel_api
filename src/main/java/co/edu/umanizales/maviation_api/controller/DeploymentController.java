package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.DeploymentDTO;
import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Deployment;
import co.edu.umanizales.maviation_api.model.Mission;
import co.edu.umanizales.maviation_api.model.enums.DeploymentStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/deployments")
public class DeploymentController {
    private static final List<Deployment> DEPLOYMENTS = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", DEPLOYMENTS));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> getActive() {
        List<Deployment> active = new ArrayList<>();
        for (Deployment d : DEPLOYMENTS) {
            if (d.getStatus() == DeploymentStatus.ACTIVE) {
                active.add(d);
            }
        }
        return ResponseEntity.ok(new ResponseDTO(true, "OK", active));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody DeploymentDTO dto) {
        String id = UUID.randomUUID().toString();
        // Create a minimal Mission to attach the provided label (no persistence for mission in this demo controller)
        Mission mission = null;
        if (dto.getMission() != null && !dto.getMission().isBlank()) {
            mission = new Mission();
            mission.setId(UUID.randomUUID().toString());
            mission.setName(dto.getMission());
        }
        Deployment deployment = new Deployment(
                id,
                null, // aircraft to be resolved at service layer by aircraftId
                null, // pilot to be resolved at service layer by pilotId
                mission,
                LocalDateTime.now(),
                DeploymentStatus.ACTIVE
        );
        DEPLOYMENTS.add(deployment);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Deployment created", Map.of("id", id)));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<ResponseDTO> complete(@PathVariable String id) {
        for (Deployment d : DEPLOYMENTS) {
            if (Objects.equals(d.getId(), id)) {
                d.setStatus(DeploymentStatus.COMPLETED);
                return ResponseEntity.ok(new ResponseDTO(true, "Deployment completed", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, "Deployment not found", null));
    }
}
