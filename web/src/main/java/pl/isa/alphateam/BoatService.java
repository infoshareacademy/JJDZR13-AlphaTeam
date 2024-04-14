package pl.isa.alphateam;

import pl.isa.alphateam.Boatsavailable;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class BoatService {
    Boatsavailable boat1 = new Boatsavailable(1742L, "Kasandra", true,999);
    Boatsavailable boat2 = new Boatsavailable(2149L, "Kleopatra", false, 998);

    public Boatsavailable getBoat(long id) {
        return Stream.of(boat1, boat2)
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Boat with given ID does not exist"));
    }
}
