package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Mission;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {
    private static final List<Mission> MISSIONS = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", MISSIONS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (Mission m : MISSIONS) {
            if (Objects.equals(m.getId(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", m));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Mission not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody Mission mission) {
        if (mission.getId() == null || mission.getId().isBlank()) {
            mission.setId(UUID.randomUUID().toString());
        }
        MISSIONS.add(mission);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Mission created", Map.of("id", mission.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody Mission updated) {
        for (Mission m : MISSIONS) {
            if (Objects.equals(m.getId(), id)) {
                updated.setId(id);
                int idx = MISSIONS.indexOf(m);
                MISSIONS.set(idx, updated);
                return ResponseEntity.ok(new ResponseDTO(true, "Mission updated", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Mission not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<Mission> it = MISSIONS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "Mission deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Mission not found", null));
    }
}
