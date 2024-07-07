package pl.isa.alphateam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.isa.alphateam.boat.Boat;

    @Repository
    public interface BoatRepository extends JpaRepository<Boat, Long> {
    }