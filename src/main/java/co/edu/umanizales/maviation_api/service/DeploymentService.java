package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Deployment;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeploymentService {
    private static final List<Deployment> DEPLOYMENTS = new ArrayList<>();

    public List<Deployment> findAll() { return DEPLOYMENTS; }

    public Optional<Deployment> findById(String id) {
        for (Deployment d : DEPLOYMENTS) {
            if (Objects.equals(d.getId(), id)) return Optional.of(d);
        }
        return Optional.empty();
    }

    public Deployment create(Deployment deployment) {
        if (deployment.getId() == null || deployment.getId().isBlank()) {
            deployment.setId(UUID.randomUUID().toString());
        }
        DEPLOYMENTS.add(deployment);
        return deployment;
    }

    public boolean update(String id, Deployment updated) {
        for (Deployment d : DEPLOYMENTS) {
            if (Objects.equals(d.getId(), id)) {
                updated.setId(id);
                DEPLOYMENTS.set(DEPLOYMENTS.indexOf(d), updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<Deployment> it = DEPLOYMENTS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) { it.remove(); return true; }
        }
        return false;
    }
}
