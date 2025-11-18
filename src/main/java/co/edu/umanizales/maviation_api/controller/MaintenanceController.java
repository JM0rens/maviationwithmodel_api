package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Maintenance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {
    private static final List<Maintenance> MAINTENANCE = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", MAINTENANCE));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (Maintenance m : MAINTENANCE) {
            if (Objects.equals(m.id(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", m));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Maintenance not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody Maintenance maintenance) {
        // record: no setters; caller must provide id; generate if blank
        String id = maintenance.id();
        if (id == null || id.isBlank()) {
            maintenance = new Maintenance(UUID.randomUUID().toString(), maintenance.aircraft(), maintenance.maintenanceType(),
                    maintenance.description(), maintenance.scheduledDate(), maintenance.completedDate(), maintenance.technician(),
                    maintenance.status(), maintenance.estimatedHours(), maintenance.actualHours(), maintenance.partsReplaced(), maintenance.cost());
        }
        MAINTENANCE.add(maintenance);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Maintenance created", Map.of("id", maintenance.id())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<Maintenance> it = MAINTENANCE.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().id(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "Maintenance deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Maintenance not found", null));
    }
}
