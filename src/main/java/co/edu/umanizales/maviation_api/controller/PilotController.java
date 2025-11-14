package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.model.Pilot;
import co.edu.umanizales.maviation_api.service.PilotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping
    public ResponseEntity<List<Pilot>> getAll() {
        return ResponseEntity.ok(pilotService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pilot> getById(@PathVariable String id) {
        Pilot found = pilotService.getById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Pilot> create(@RequestBody Pilot pilot) {
        Pilot created = pilotService.create(pilot);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pilot> update(@PathVariable String id, @RequestBody Pilot pilot) {
        Pilot updated = pilotService.update(id, pilot);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean removed = pilotService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
