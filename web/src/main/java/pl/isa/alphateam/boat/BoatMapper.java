package pl.isa.alphateam.boat;

public class BoatMapper {
    public static BoatDto mapToBoatDto(Boat boat){
        return BoatDto.builder()
                .boatId(boat.getBoatId())
                .name(boat.getName())
                .capacity(boat.getCapacity())
                .costPerDay(boat.getCostPerDay())
                .isAvailable(boat.getIsAvailable())
                .build();
    }


    public static Boat mapToBoat(BoatDto boatDto){
        return Boat.builder()
                .name(boatDto.getName())
                .capacity(boatDto.getCapacity())
                .costPerDay(boatDto.getCostPerDay())
                .isAvailable(boatDto.isAvailable())
                .build();
    }
}
