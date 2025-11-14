package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Mission;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MissionService {
    private final List<Mission> missions = new ArrayList<>();

    public List<Mission> getAll() {
        return missions;
    }

    public Mission getById(String id) {
        for (Mission m : missions) {
            if (m != null && m.getId() != null && m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public Mission create(Mission mission) {
        if (mission == null) {
            return null;
        }
        Mission existing = getById(mission.getId());
        if (existing == null) {
            missions.add(mission);
        }
        return mission;
    }

    public Mission update(String id, Mission updated) {
        if (id == null || updated == null) {
            return null;
        }
        for (int i = 0; i < missions.size(); i++) {
            Mission current = missions.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                missions.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        int indexToRemove = -1;
        for (int i = 0; i < missions.size(); i++) {
            Mission current = missions.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove >= 0) {
            missions.remove(indexToRemove);
            return true;
        }
        return false;
    }
}
