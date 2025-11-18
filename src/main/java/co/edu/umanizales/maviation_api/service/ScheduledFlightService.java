package co.edu.umanizales.maviation_api.service;

import co.edu.umanizales.maviation_api.model.ScheduledFlight;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduledFlightService {
    private static final List<ScheduledFlight> FLIGHTS = new ArrayList<>();

    public List<ScheduledFlight> findAll() { return FLIGHTS; }

    public Optional<ScheduledFlight> findById(String id) {
        for (ScheduledFlight f : FLIGHTS) {
            if (Objects.equals(f.getId(), id)) return Optional.of(f);
        }
        return Optional.empty();
    }

    public ScheduledFlight create(ScheduledFlight flight) {
        if (flight.getId() == null || flight.getId().isBlank()) flight.setId(UUID.randomUUID().toString());
        FLIGHTS.add(flight);
        return flight;
    }

    public boolean update(String id, ScheduledFlight updated) {
        for (ScheduledFlight f : FLIGHTS) {
            if (Objects.equals(f.getId(), id)) {
                updated.setId(id);
                FLIGHTS.set(FLIGHTS.indexOf(f), updated);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(String id) {
        Iterator<ScheduledFlight> it = FLIGHTS.iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next().getId(), id)) { it.remove(); return true; }
        }
        return false;
    }
}
