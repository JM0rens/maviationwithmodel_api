package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.AircraftDTO;
import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
@CrossOrigin(origins = "*")
public class AircraftController {
    
    @Autowired
    private AircraftService aircraftService;
    
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllAircraft() {
        try {
            List<AircraftDTO> aircraft = aircraftService.getAllAircraft();
            return ResponseEntity.ok(new ResponseDTO(true, "Aircraft retrieved successfully", aircraft));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving aircraft: " + e.getMessage(), null));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getAircraftById(@PathVariable String id) {
        try {
            return aircraftService.getAircraftById(id)
                .map(aircraft -> ResponseEntity.ok(new ResponseDTO(true, "Aircraft found", aircraft)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, "Aircraft not found", null)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving aircraft: " + e.getMessage(), null));
        }
    }
    
    @GetMapping("/available")
    public ResponseEntity<ResponseDTO> getAvailableAircraft() {
        try {
            List<AircraftDTO> aircraft = aircraftService.getAvailableAircraft();
            return ResponseEntity.ok(new ResponseDTO(true, "Available aircraft retrieved successfully", aircraft));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving available aircraft: " + e.getMessage(), null));
        }
    }
    
    @PostMapping
    public ResponseEntity<ResponseDTO> createAircraft(@RequestBody AircraftDTO aircraftDTO) {
        try {
            AircraftDTO created = aircraftService.createAircraft(aircraftDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Aircraft created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(false, "Error creating aircraft: " + e.getMessage(), null));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateAircraft(@PathVariable String id, @RequestBody AircraftDTO aircraftDTO) {
        try {
            AircraftDTO updated = aircraftService.updateAircraft(id, aircraftDTO);
            return ResponseEntity.ok(new ResponseDTO(true, "Aircraft updated successfully", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error updating aircraft: " + e.getMessage(), null));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteAircraft(@PathVariable String id) {
        try {
            aircraftService.deleteAircraft(id);
            return ResponseEntity.ok(new ResponseDTO(true, "Aircraft deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error deleting aircraft: " + e.getMessage(), null));
        }
    }
}
