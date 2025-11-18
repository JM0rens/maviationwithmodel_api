package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Armament;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArmamentService {
    private static final List<Armament> ARMAMENTS = new ArrayList<>();

    public List<Armament> findAll() { return ARMAMENTS; }

    public Optional<Armament> findById(String id) {
        for (Armament a : ARMAMENTS) {
            if (Objects.equals(a.getId(), id)) return Optional.of(a);
        }
        return Optional.empty();
    }

    public Armament create(Armament armament) {
        if (armament.getId() == null || armament.getId().isBlank()) armament.setId(UUID.randomUUID().toString());
        ARMAMENTS.add(armament);
        return armament;
    }

    public boolean update(String id, Armament updated) {
        for (Armament a : ARMAMENTS) {
            if (Objects.equals(a.getId(), id)) {
                updated.setId(id);
                ARMAMENTS.set(ARMAMENTS.indexOf(a), updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<Armament> it = ARMAMENTS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) { it.remove(); return true; }
        }
        return false;
    }
}
