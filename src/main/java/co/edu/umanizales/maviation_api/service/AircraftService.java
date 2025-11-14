package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.Aircraft;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftService {
    private final List<Aircraft> aircraftList = new ArrayList<>();

    public List<Aircraft> getAll() {
        return aircraftList;
    }

    public Aircraft getById(String id) {
        for (Aircraft ac : aircraftList) {
            if (ac != null && ac.getId() != null && ac.getId().equals(id)) {
                return ac;
            }
        }
        return null;
    }

    public Aircraft create(Aircraft aircraft) {
        if (aircraft == null) {
            return null;
        }
        // avoid duplicates by id
        Aircraft existing = getById(aircraft.getId());
        if (existing == null) {
            aircraftList.add(aircraft);
        }
        return aircraft;
    }

    public Aircraft update(String id, Aircraft updated) {
        if (id == null || updated == null) {
            return null;
        }
        for (int i = 0; i < aircraftList.size(); i++) {
            Aircraft current = aircraftList.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                aircraftList.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public boolean delete(String id) {
        int indexToRemove = -1;
        for (int i = 0; i < aircraftList.size(); i++) {
            Aircraft current = aircraftList.get(i);
            if (current != null && current.getId() != null && current.getId().equals(id)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove >= 0) {
            aircraftList.remove(indexToRemove);
            return true;
        }
        return false;
    }
}
