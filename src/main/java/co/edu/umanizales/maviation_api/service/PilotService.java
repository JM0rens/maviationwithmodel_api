package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Pilot;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PilotService {
    private final List<Pilot> pilots = new ArrayList<>();

    public List<Pilot> getAll() {
        return pilots;
    }

    public Pilot getById(String id) {
        for (Pilot p : pilots) {
            if (p != null && p.getId() != null && p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Pilot create(Pilot pilot) {
        if (pilot == null) {
            return null;
        }
        Pilot existing = getById(pilot.getId());
        if (existing == null) {
            pilots.add(pilot);
        }
        return pilot;
    }

    public Pilot update(String id, Pilot updated) {
        if (id == null || updated == null) {
            return null;
        }
        for (int i = 0; i < pilots.size(); i++) {
            Pilot current = pilots.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                pilots.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        int indexToRemove = -1;
        for (int i = 0; i < pilots.size(); i++) {
            Pilot current = pilots.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove >= 0) {
            pilots.remove(indexToRemove);
            return true;
        }
        return false;
    }
}
