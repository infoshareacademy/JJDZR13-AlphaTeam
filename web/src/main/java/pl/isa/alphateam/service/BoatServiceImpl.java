package pl.isa.alphateam.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.boat.BoatDto;
import pl.isa.alphateam.boat.BoatMapper;
import pl.isa.alphateam.parser.JSONParserBoat;
import pl.isa.alphateam.repository.BoatRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor//added as there were some issues https://www.baeldung.com/java-lombok-constructor-annotation
public class BoatServiceImpl implements BoatService {

    private BoatRepository boatRepository;

    public BoatServiceImpl(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @Override
    public List<BoatDto> findAllBoats() {
        List<Boat> boats = boatRepository.findAll();
        return boats.stream()
                .map(BoatMapper::mapToBoatDto)
                .collect(Collectors.toList());

    }

 /*   @Override
    public BoatDto findPostById(Long boatId) {
        Boat boat = boatRepository.findById(boatId).get();
        return BoatMapper.mapToBoatDto(boat);
    }*/

    //gets boats from JSON
    @Override
    public List<BoatDto> findAllAvailableBoats(LocalDate startDate, LocalDate endDate) {
        List<BoatDto> boatDtoList = getBoatDtosWithNADates();
        List<LocalDate> datesInTimeRange = getListOfDaysForPeriod(startDate, endDate);

        Iterator<BoatDto> iterator = boatDtoList.iterator();
        while (iterator.hasNext()) {
            BoatDto boatDto = iterator.next();
            int noOfNAdates =boatDto.getNotAvailableDates()!=null? (int) boatDto.getNotAvailableDates().stream().filter(date -> datesInTimeRange.contains(date)).count():0;
            if (noOfNAdates > 0) {
                iterator.remove();
            }
        }

        return boatDtoList;
    }

    public List<LocalDate> getListOfDaysForPeriod(LocalDate startDate, LocalDate endDate) {
        long daysNo = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        List<LocalDate> listOfDaysInPeriod = new ArrayList<>();
        for (long i = 0; i <= daysNo; i++) {
            listOfDaysInPeriod.add(startDate.plusDays(i));
        }
        return listOfDaysInPeriod;
    }

    private static List<BoatDto> getBoatDtosWithNADates() {
        List<Boat> boats = new ArrayList<>(JSONParserBoat.getListOfBoatsFromDatabase());
        List<BoatDto> boatDtoList = new ArrayList<>();
        for (Boat boat : boats) {
            BoatDto boatDto = BoatMapper.mapToBoatDto(boat);
            List<LocalDate> notAvailableDays = BoatReservationService.getListOFDatesNAforBoatId(boat.getBoatId());
            if (!notAvailableDays.isEmpty()) {
                boatDto.setNotAvailableDates(notAvailableDays);
            }
            boatDtoList.add(boatDto);
        }
        return boatDtoList;
    }
}
