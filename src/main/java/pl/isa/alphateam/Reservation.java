package pl.isa.alphateam;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;


public class Reservation {
    private LocalDate startDate;
    private LocalDate endDate;
    private Customer customer;
    private Boat boat;

    public Reservation(LocalDate startDate, LocalDate endDate, Customer customer, Boat boat) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.boat = boat;
    }



    public Double getCostOfReservation() {
    long dayNo = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        return boat.getCostPerDay()*dayNo;
    }

    public String getUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println("Twój kod potrzebny do rezerwacji to: " + uuid);
        System.out.println("Wpisz go po zarejestrowaniu");
        return null;
    }





}
