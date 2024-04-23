package pl.isa.alphateam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.boat.BoatDto;

import java.util.Optional;


public interface BoatRepository extends JpaRepository<Boat, Long> {
    Optional<BoatDto> findByUrl(String url);
}