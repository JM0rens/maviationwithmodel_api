package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.AircraftDTO;
import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Aircraft;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {
    private static final List<Aircraft> AIRCRAFT = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", AIRCRAFT));
    }

    @GetMapping("/available")
    public ResponseEntity<ResponseDTO> getAvailable() {
        List<Aircraft> available = new ArrayList<>();
        for (Aircraft a : AIRCRAFT) { // enhanced for
            if ("ACTIVE".equalsIgnoreCase(a.getStatus())) {
                available.add(a);
            }
        }
        return ResponseEntity.ok(new ResponseDTO(true, "OK", available));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (Aircraft a : AIRCRAFT) { // enhanced for
            if (Objects.equals(a.getId(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", a));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, "Aircraft not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody AircraftDTO dto) {
        String id = UUID.randomUUID().toString();
        Aircraft aircraft = new Aircraft(
                id,
                dto.getName(),
                dto.getStatus(),
                dto.getType(),
                dto.getModel(),
                dto.getHangarNumber(),
                null, // Pilot will be resolved by service layer if needed
                dto.getMission()
        );
        AIRCRAFT.add(aircraft);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Aircraft created", Map.of("id", id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody AircraftDTO dto) {
        for (Aircraft existing : AIRCRAFT) { // enhanced for
            if (Objects.equals(existing.getId(), id)) {
                existing.setName(dto.getName());
                existing.setStatus(dto.getStatus());
                existing.setType(dto.getType() != null ? co.edu.umanizales.maviation_api.model.enums.AircraftType.valueOf(dto.getType()) : null);
                existing.setModel(dto.getModel());
                existing.setHangarNumber(dto.getHangarNumber());
                existing.setMission(dto.getMission());
                return ResponseEntity.ok(new ResponseDTO(true, "Aircraft updated", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Aircraft not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<Aircraft> it = AIRCRAFT.iterator();
        for (Aircraft a : AIRCRAFT) { // enhanced for (read-only iteration)
            if (Objects.equals(a.getId(), id)) {
                // need iterator to remove safely
                it = AIRCRAFT.iterator();
                while (it.hasNext()) {
                    if (Objects.equals(it.next().getId(), id)) {
                        it.remove();
                        break;
                    }
                }
                return ResponseEntity.ok(new ResponseDTO(true, "Aircraft deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "Aircraft not found", null));
    }
}
