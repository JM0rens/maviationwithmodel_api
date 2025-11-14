package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.model.Armament;
import co.edu.umanizales.maviation_api.service.ArmamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/armaments")
public class ArmamentController {

    private final ArmamentService armamentService;

    public ArmamentController(ArmamentService armamentService) {
        this.armamentService = armamentService;
    }

    @GetMapping
    public ResponseEntity<List<Armament>> getAll() {
        return ResponseEntity.ok(armamentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Armament> getById(@PathVariable String id) {
        Armament found = armamentService.getById(id);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<Armament> create(@RequestBody Armament armament) {
        Armament created = armamentService.create(armament);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Armament> update(@PathVariable String id, @RequestBody Armament armament) {
        Armament updated = armamentService.update(id, armament);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean removed = armamentService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
