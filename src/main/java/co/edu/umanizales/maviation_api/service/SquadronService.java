package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Squadron;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SquadronService {
    private static final List<Squadron> SQUADRONS = new ArrayList<>();

    public List<Squadron> findAll() { return SQUADRONS; }

    public Optional<Squadron> findById(String id) {
        for (Squadron s : SQUADRONS) {
            if (Objects.equals(s.getId(), id)) return Optional.of(s);
        }
        return Optional.empty();
    }

    public Squadron create(Squadron squadron) {
        if (squadron.getId() == null || squadron.getId().isBlank()) squadron.setId(UUID.randomUUID().toString());
        SQUADRONS.add(squadron);
        return squadron;
    }

    public boolean update(String id, Squadron updated) {
        for (Squadron s : SQUADRONS) {
            if (Objects.equals(s.getId(), id)) {
                updated.setId(id);
                SQUADRONS.set(SQUADRONS.indexOf(s), updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<Squadron> it = SQUADRONS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) { it.remove(); return true; }
        }
        return false;
    }
}
