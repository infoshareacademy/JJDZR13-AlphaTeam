package pl.isa.alphateam;

import java.time.Duration;
import java.time.LocalDate;


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



}
