package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Squadron;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/squadrons")
public class SquadronController {
    private static final List<Squadron> SQUADRONS = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", SQUADRONS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (Squadron s : SQUADRONS) {
            if (Objects.equals(s.getId(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", s));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Squadron not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody Squadron squadron) {
        if (squadron.getId() == null || squadron.getId().isBlank()) {
            squadron.setId(UUID.randomUUID().toString());
        }
        SQUADRONS.add(squadron);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Squadron created", Map.of("id", squadron.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody Squadron updated) {
        for (Squadron s : SQUADRONS) {
            if (Objects.equals(s.getId(), id)) {
                updated.setId(id);
                int idx = SQUADRONS.indexOf(s);
                SQUADRONS.set(idx, updated);
                return ResponseEntity.ok(new ResponseDTO(true, "Squadron updated", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Squadron not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<Squadron> it = SQUADRONS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "Squadron deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Squadron not found", null));
    }
}
