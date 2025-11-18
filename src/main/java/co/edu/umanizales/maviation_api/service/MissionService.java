package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Mission;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MissionService {
    private static final List<Mission> MISSIONS = new ArrayList<>();

    public List<Mission> findAll() { return MISSIONS; }

    public Optional<Mission> findById(String id) {
        for (Mission m : MISSIONS) {
            if (Objects.equals(m.getId(), id)) return Optional.of(m);
        }
        return Optional.empty();
    }

    public Mission create(Mission mission) {
        if (mission.getId() == null || mission.getId().isBlank()) mission.setId(UUID.randomUUID().toString());
        MISSIONS.add(mission);
        return mission;
    }

    public boolean update(String id, Mission updated) {
        for (Mission m : MISSIONS) {
            if (Objects.equals(m.getId(), id)) {
                updated.setId(id);
                MISSIONS.set(MISSIONS.indexOf(m), updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<Mission> it = MISSIONS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) { it.remove(); return true; }
        }
        return false;
    }
}
