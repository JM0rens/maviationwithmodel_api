package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Pilot;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PilotService {
    private static final List<Pilot> PILOTS = new ArrayList<>();

    public List<Pilot> findAll() { return PILOTS; }

    public Optional<Pilot> findById(String id) {
        for (Pilot p : PILOTS) { // enhanced for
            if (Objects.equals(p.getId(), id)) return Optional.of(p);
        }
        return Optional.empty();
    }

    public Pilot create(Pilot pilot) {
        if (pilot.getId() == null || pilot.getId().isBlank()) pilot.setId(UUID.randomUUID().toString());
        PILOTS.add(pilot);
        return pilot;
    }

    public boolean update(String id, Pilot updated) {
        for (Pilot p : PILOTS) {
            if (Objects.equals(p.getId(), id)) {
                updated.setId(id);
                int idx = PILOTS.indexOf(p);
                PILOTS.set(idx, updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<Pilot> it = PILOTS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) { it.remove(); return true; }
        }
        return false;
    }
}
