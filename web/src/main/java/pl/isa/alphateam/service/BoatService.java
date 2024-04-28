package pl.isa.alphateam.service;

import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.boat.BoatDto;

import java.time.LocalDate;
import java.util.List;

public interface BoatService {
    List<BoatDto> findAllBoats();


   // BoatDto findPostById(Long boatId);
    List<BoatDto> findAllAvailableBoats(LocalDate from, LocalDate to);

}
