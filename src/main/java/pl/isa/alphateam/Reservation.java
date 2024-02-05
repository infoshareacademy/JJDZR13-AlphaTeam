package pl.isa.alphateam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.JSONParserBoat.saveBoatInDatabase;


public class Reservation {
    private LocalDate startDate;
    private LocalDate endDate;
    private Customer customer;
    private Boat boat;

    public Reservation() {
    }

    public Reservation(LocalDate startDate, LocalDate endDate, Customer customer, Boat boat) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.boat = boat;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "startDate='" + startDate + '\'' +
                ", endDate=" + endDate +
                ", customer=" + customer +
                ", boat='" + boat + '\'' +
                '}';
    }


    @JsonProperty("startDate")
    public String getStartDateProperty() {
        return startDate.toString();
    }

    public void setStartDateProperty(String date) {
        this.startDate = LocalDate.parse(date);
    }

    @JsonProperty("endDate")
    public String getEndDateProperty() {
        return endDate.toString();
    }


    public void setEndDateProperty(String date) {
        this.endDate = LocalDate.parse(date);
    }

    @JsonIgnore
    public LocalDate getStartDate() {
        return startDate;
    }

    @JsonIgnore
    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    @JsonIgnore
    public Double getCostOfReservation() {
        long dayNo = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        return boat.getCostPerDay() * dayNo;
    }



}
