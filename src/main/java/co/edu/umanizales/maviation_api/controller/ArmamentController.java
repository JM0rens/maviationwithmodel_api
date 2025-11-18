package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Armament;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/armaments")
public class ArmamentController {
    private static final List<Armament> ARMAMENTS = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", ARMAMENTS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (Armament a : ARMAMENTS) {
            if (Objects.equals(a.getId(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", a));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Armament not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody Armament armament) {
        if (armament.getId() == null || armament.getId().isBlank()) {
            armament.setId(UUID.randomUUID().toString());
        }
        ARMAMENTS.add(armament);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Armament created", Map.of("id", armament.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody Armament updated) {
        for (Armament a : ARMAMENTS) {
            if (Objects.equals(a.getId(), id)) {
                updated.setId(id);
                int idx = ARMAMENTS.indexOf(a);
                ARMAMENTS.set(idx, updated);
                return ResponseEntity.ok(new ResponseDTO(true, "Armament updated", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Armament not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<Armament> it = ARMAMENTS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "Armament deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Armament not found", null));
    }
}
