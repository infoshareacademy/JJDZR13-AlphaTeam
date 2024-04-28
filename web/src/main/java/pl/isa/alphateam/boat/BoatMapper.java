package pl.isa.alphateam.boat;

import java.util.List;
import java.util.stream.Collectors;

public class BoatMapper {
    public static BoatDto mapToBoatDto(Boat boat) {
        return BoatDto.builder()
                .boatId(boat.getBoatId())
                .name(boat.getName())
                .capacity(boat.getCapacity())
                .costPerDay(boat.getCostPerDay())
                .isAvailable(boat.getIsAvailable())
                .build();
    }


    public static Boat mapToBoat(BoatDto boatDto) {
        return Boat.builder()
                .boatId(boatDto.getBoatId())
                .name(boatDto.getName())
                .capacity(boatDto.getCapacity())
                .costPerDay(boatDto.getCostPerDay())
                .isAvailable(boatDto.isAvailable())
                .build();
    }

    public static List<Boat> mapToBoat(List<BoatDto> list) {
        return list.stream().map(boat -> mapToBoat(boat)).collect(Collectors.toList());
    }
}
