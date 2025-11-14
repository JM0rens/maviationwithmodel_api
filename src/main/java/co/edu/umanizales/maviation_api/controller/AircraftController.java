package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.model.Aircraft;
import co.edu.umanizales.maviation_api.service.AircraftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public ResponseEntity<List<Aircraft>> getAll() {
        return ResponseEntity.ok(aircraftService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getById(@PathVariable String id) {
        Aircraft found = aircraftService.getById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Aircraft> create(@RequestBody Aircraft aircraft) {
        Aircraft created = aircraftService.create(aircraft);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> update(@PathVariable String id, @RequestBody Aircraft aircraft) {
        Aircraft updated = aircraftService.update(id, aircraft);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean removed = aircraftService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
