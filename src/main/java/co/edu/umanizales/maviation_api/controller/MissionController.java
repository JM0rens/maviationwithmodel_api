package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.model.Mission;
import co.edu.umanizales.maviation_api.service.MissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public ResponseEntity<List<Mission>> getAll() {
        return ResponseEntity.ok(missionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getById(@PathVariable String id) {
        Mission found = missionService.getById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Mission> create(@RequestBody Mission mission) {
        Mission created = missionService.create(mission);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mission> update(@PathVariable String id, @RequestBody Mission mission) {
        Mission updated = missionService.update(id, mission);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean removed = missionService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
