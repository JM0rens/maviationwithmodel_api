package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.AirBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/airbases")
public class AirBaseController {
    private static final List<AirBase> AIRBASES = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", AIRBASES));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (AirBase ab : AIRBASES) { // enhanced for
            if (Objects.equals(ab.getId(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", ab));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "AirBase not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody AirBase airBase) {
        if (airBase.getId() == null || airBase.getId().isBlank()) {
            airBase.setId(UUID.randomUUID().toString());
        }
        AIRBASES.add(airBase);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "AirBase created", Map.of("id", airBase.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody AirBase updated) {
        for (int i = 0; i < AIRBASES.size(); i++) {
            AirBase current = AIRBASES.get(i);
            if (Objects.equals(current.getId(), id)) {
                updated.setId(id);
                AIRBASES.set(i, updated);
                return ResponseEntity.ok(new ResponseDTO(true, "AirBase updated", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "AirBase not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<AirBase> it = AIRBASES.iterator();
        while (it.hasNext()) { // enhanced style over Iterator
            AirBase ab = it.next();
            if (Objects.equals(ab.getId(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "AirBase deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "AirBase not found", null));
    }
}
