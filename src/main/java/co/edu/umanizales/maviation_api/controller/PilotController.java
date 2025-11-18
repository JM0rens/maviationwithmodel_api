package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.PilotDTO;
import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Pilot;
import co.edu.umanizales.maviation_api.model.enums.AircraftType;
import co.edu.umanizales.maviation_api.model.enums.MilitaryRank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {
    private static final List<Pilot> PILOTS = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", PILOTS));
    }

    @GetMapping("/available")
    public ResponseEntity<ResponseDTO> getAvailable() {
        List<Pilot> available = new ArrayList<>();
        for (Pilot p : PILOTS) {
            if ("ACTIVE".equalsIgnoreCase(p.getStatus())) {
                available.add(p);
            }
        }
        return ResponseEntity.ok(new ResponseDTO(true, "OK", available));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (Pilot p : PILOTS) {
            if (Objects.equals(p.getId(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", p));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, "Pilot not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody PilotDTO dto) {
        String id = UUID.randomUUID().toString();
        MilitaryRank rank = dto.getRank() != null ? MilitaryRank.valueOf(dto.getRank()) : null;
        AircraftType specialization = dto.getSpecialization() != null ? AircraftType.valueOf(dto.getSpecialization()) : null;
        Pilot pilot = new Pilot(id, dto.getName(), dto.getStatus(),
                dto.getRank(), dto.getFlightHours() != null ? dto.getFlightHours() : 0,
                dto.getSpecialization());
        // Ensure enums parsed if provided
        pilot.setRank(rank);
        pilot.setSpecialization(specialization);
        PILOTS.add(pilot);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Pilot created", Map.of("id", id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody PilotDTO dto) {
        for (Pilot p : PILOTS) {
            if (Objects.equals(p.getId(), id)) {
                p.setName(dto.getName());
                p.setStatus(dto.getStatus());
                if (dto.getRank() != null) p.setRank(MilitaryRank.valueOf(dto.getRank()));
                if (dto.getSpecialization() != null) p.setSpecialization(AircraftType.valueOf(dto.getSpecialization()));
                if (dto.getFlightHours() != null) p.setFlightHours(dto.getFlightHours());
                return ResponseEntity.ok(new ResponseDTO(true, "Pilot updated", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, "Pilot not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<Pilot> it = PILOTS.iterator();
        while (it.hasNext()) { // enhanced iteration over iterator
            if (Objects.equals(it.next().getId(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "Pilot deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, "Pilot not found", null));
    }
}
