package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.PilotDTO;
import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilots")
@CrossOrigin(origins = "*")
public class PilotController {
    
    @Autowired
    private PilotService pilotService;
    
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllPilots() {
        try {
            List<PilotDTO> pilots = pilotService.getAllPilots();
            return ResponseEntity.ok(new ResponseDTO(true, "Pilots retrieved successfully", pilots));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving pilots: " + e.getMessage(), null));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPilotById(@PathVariable String id) {
        try {
            return pilotService.getPilotById(id)
                .map(pilot -> ResponseEntity.ok(new ResponseDTO(true, "Pilot found", pilot)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Pilot not found", null)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving pilot: " + e.getMessage(), null));
        }
    }
    
    @GetMapping("/available")
    public ResponseEntity<ResponseDTO> getAvailablePilots() {
        try {
            List<PilotDTO> pilots = pilotService.getAvailablePilots();
            return ResponseEntity.ok(new ResponseDTO(true, "Available pilots retrieved successfully", pilots));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving available pilots: " + e.getMessage(), null));
        }
    }
    
    @PostMapping
    public ResponseEntity<ResponseDTO> createPilot(@RequestBody PilotDTO pilotDTO) {
        try {
            PilotDTO created = pilotService.createPilot(pilotDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Pilot created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(false, "Error creating pilot: " + e.getMessage(), null));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePilot(@PathVariable String id, @RequestBody PilotDTO pilotDTO) {
        try {
            PilotDTO updated = pilotService.updatePilot(id, pilotDTO);
            return ResponseEntity.ok(new ResponseDTO(true, "Pilot updated successfully", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error updating pilot: " + e.getMessage(), null));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePilot(@PathVariable String id) {
        try {
            pilotService.deletePilot(id);
            return ResponseEntity.ok(new ResponseDTO(true, "Pilot deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error deleting pilot: " + e.getMessage(), null));
        }
    }
}
