package co.edu.umanizales.maviation_api.controller;

import co.edu.umanizales.maviation_api.dto.DeploymentDTO;
import co.edu.umanizales.maviation_api.dto.ResponseDTO;
import co.edu.umanizales.maviation_api.model.Deployment;
import co.edu.umanizales.maviation_api.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deployments")
@CrossOrigin(origins = "*")
public class DeploymentController {
    
    @Autowired
    private DeploymentService deploymentService;
    
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllDeployments() {
        try {
            List<Deployment> deployments = deploymentService.getAllDeployments();
            return ResponseEntity.ok(new ResponseDTO(true, "Deployments retrieved successfully", deployments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving deployments: " + e.getMessage(), null));
        }
    }
    
    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> getActiveDeployments() {
        try {
            List<Deployment> deployments = deploymentService.getActiveDeployments();
            return ResponseEntity.ok(new ResponseDTO(true, "Active deployments retrieved successfully", deployments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error retrieving active deployments: " + e.getMessage(), null));
        }
    }
    
    @PostMapping
    public ResponseEntity<ResponseDTO> deployAircraft(@RequestBody DeploymentDTO deploymentDTO) {
        try {
            Deployment deployment = deploymentService.deployAircraft(deploymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(true, "Aircraft deployed successfully", deployment));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDTO(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error deploying aircraft: " + e.getMessage(), null));
        }
    }
    
    @PutMapping("/{id}/complete")
    public ResponseEntity<ResponseDTO> completeDeployment(@PathVariable String id) {
        try {
            deploymentService.completeDeployment(id);
            return ResponseEntity.ok(new ResponseDTO(true, "Deployment completed successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDTO(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDTO(false, "Error completing deployment: " + e.getMessage(), null));
        }
    }
}
