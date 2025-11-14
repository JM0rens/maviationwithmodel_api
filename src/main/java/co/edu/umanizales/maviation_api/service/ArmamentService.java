package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Armament;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArmamentService {
    private final List<Armament> armaments = new ArrayList<>();

    public List<Armament> getAll() {
        return armaments;
    }

    public Armament getById(String id) {
        for (Armament a : armaments) {
            if (a != null && a.getId() != null && a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public Armament create(Armament armament) {
        if (armament == null) {
            return null;
        }
        Armament existing = getById(armament.getId());
        if (existing == null) {
            armaments.add(armament);
        }
        return armament;
    }

    public Armament update(String id, Armament updated) {
        if (id == null || updated == null) {
            return null;
        }
        for (int i = 0; i < armaments.size(); i++) {
            Armament current = armaments.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                armaments.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        int indexToRemove = -1;
        for (int i = 0; i < armaments.size(); i++) {
            Armament current = armaments.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove >= 0) {
            armaments.remove(indexToRemove);
            return true;
        }
        return false;
    }
}
