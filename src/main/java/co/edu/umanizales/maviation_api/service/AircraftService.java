package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Aircraft;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AircraftService {
    private static final List<Aircraft> AIRCRAFT = new ArrayList<>();

    public List<Aircraft> findAll() {
        return AIRCRAFT;
    }

    public Optional<Aircraft> findById(String id) {
        for (Aircraft a : AIRCRAFT) { // enhanced for
            if (Objects.equals(a.getId(), id)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    public Aircraft create(Aircraft aircraft) {
        if (aircraft.getId() == null || aircraft.getId().isBlank()) {
            aircraft.setId(UUID.randomUUID().toString());
        }
        AIRCRAFT.add(aircraft);
        return aircraft;
    }

    public boolean update(String id, Aircraft updated) {
        for (Aircraft a : AIRCRAFT) {
            if (Objects.equals(a.getId(), id)) {
                updated.setId(id);
                int idx = AIRCRAFT.indexOf(a);
                AIRCRAFT.set(idx, updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<Aircraft> it = AIRCRAFT.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
