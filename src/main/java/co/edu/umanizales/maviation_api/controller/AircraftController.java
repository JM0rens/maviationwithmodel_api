package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.AircraftDTO;
import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Aircraft;
import co.edu.umanizales.maviation_api.repository.AircraftRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {
    private final AircraftRepository aircraftRepository;

    public AircraftController(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        List<Aircraft> all = aircraftRepository.findAll();
        return ResponseEntity.ok(new ResponseDTO(true, "OK", all));
    }

    @GetMapping("/available")
    public ResponseEntity<ResponseDTO> getAvailable() {
        List<Aircraft> all = aircraftRepository.findAll();
        List<Aircraft> available = new ArrayList<>();
        for (Aircraft a : all) {
            if ("ACTIVE".equalsIgnoreCase(a.getStatus())) {
                available.add(a);
            }
        }
        return ResponseEntity.ok(new ResponseDTO(true, "OK", available));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        return aircraftRepository.findAll().stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findFirst()
                .<ResponseEntity<ResponseDTO>>map(a -> ResponseEntity.ok(new ResponseDTO(true, "OK", a)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(false, "Aircraft not found", null)));
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
        aircraftRepository.save(aircraft);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Aircraft created", Map.of("id", id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String id, @RequestBody AircraftDTO dto) {
        List<Aircraft> all = new ArrayList<>(aircraftRepository.findAll());
        Optional<Aircraft> opt = all.stream().filter(a -> Objects.equals(a.getId(), id)).findFirst();
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Aircraft not found", null));
        }
        Aircraft existing = opt.get();
        existing.setName(dto.getName());
        existing.setStatus(dto.getStatus());
        existing.setType(dto.getType() != null ? co.edu.umanizales.maviation_api.model.enums.AircraftType.valueOf(dto.getType()) : null);
        existing.setModel(dto.getModel());
        existing.setHangarNumber(dto.getHangarNumber());
        existing.setMission(dto.getMission());
        // Pilot resolution by id (dto.getPilotId()) omitted intentionally for simplicity
        aircraftRepository.saveAll(all);
        return ResponseEntity.ok(new ResponseDTO(true, "Aircraft updated", Map.of("id", id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        aircraftRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseDTO(true, "Aircraft deleted", Map.of("id", id)));
    }
}
