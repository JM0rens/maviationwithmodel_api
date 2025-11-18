package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.TacticalReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/tactical-reports")
public class TacticalReportController {
    private static final List<TacticalReport> REPORTS = new ArrayList<>();

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(new ResponseDTO(true, "OK", REPORTS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable String id) {
        for (TacticalReport r : REPORTS) {
            if (Objects.equals(r.id(), id)) {
                return ResponseEntity.ok(new ResponseDTO(true, "OK", r));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "TacticalReport not found", null));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody TacticalReport report) {
        String id = report.id();
        if (id == null || id.isBlank()) {
            report = new TacticalReport(UUID.randomUUID().toString(), report.mission(), report.reporter(), report.reportDate(),
                    report.reportType(), report.title(), report.content(), report.priority(), report.classification(),
                    report.targetDetails(), report.enemyActivity(), report.weatherConditions(), report.recommendations());
        }
        REPORTS.add(report);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "TacticalReport created", Map.of("id", report.id())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String id) {
        Iterator<TacticalReport> it = REPORTS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().id(), id)) {
                it.remove();
                return ResponseEntity.ok(new ResponseDTO(true, "TacticalReport deleted", Map.of("id", id)));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(false, "TacticalReport not found", null));
    }
}
