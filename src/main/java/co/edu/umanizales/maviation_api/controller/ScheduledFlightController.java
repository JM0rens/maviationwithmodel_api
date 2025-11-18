package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.ScheduledFlight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/scheduled-flights")
public class ScheduledFlightController {
    private static final List<ScheduledFlight> FLIGHTS = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", FLIGHTS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (ScheduledFlight f : FLIGHTS) {
            if (Objects.equals(f.getId(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", f));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "ScheduledFlight not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody ScheduledFlight flight) {
        if (flight.getId() == null || flight.getId().isBlank()) {
            flight.setId(UUID.randomUUID().toString());
        }
        FLIGHTS.add(flight);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "ScheduledFlight created", Map.of("id", flight.getId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody ScheduledFlight updated) {
        for (ScheduledFlight f : FLIGHTS) {
            if (Objects.equals(f.getId(), id)) {
                updated.setId(id);
                int idx = FLIGHTS.indexOf(f);
                FLIGHTS.set(idx, updated);
                return ResponseEntity.ok(new ResponseDTO(true, "ScheduledFlight updated", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "ScheduledFlight not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<ScheduledFlight> it = FLIGHTS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "ScheduledFlight deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "ScheduledFlight not found", null));
    }
}
