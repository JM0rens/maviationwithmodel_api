package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.AirBase;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AirBaseService {
    private static final List<AirBase> AIRBASES = new ArrayList<>();

    public List<AirBase> findAll() { return AIRBASES; }

    public Optional<AirBase> findById(String id) {
        for (AirBase a : AIRBASES) {
            if (Objects.equals(a.getId(), id)) return Optional.of(a);
        }
        return Optional.empty();
    }

    public AirBase create(AirBase airBase) {
        if (airBase.getId() == null || airBase.getId().isBlank()) {
            airBase.setId(UUID.randomUUID().toString());
        }
        AIRBASES.add(airBase);
        return airBase;
    }

    public boolean update(String id, AirBase updated) {
        for (AirBase a : AIRBASES) {
            if (Objects.equals(a.getId(), id)) {
                updated.setId(id);
                AIRBASES.set(AIRBASES.indexOf(a), updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<AirBase> it = AIRBASES.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) { it.remove(); return true; }
        }
        return false;
    }
}
