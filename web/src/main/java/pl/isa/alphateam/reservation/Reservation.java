package pl.isa.alphateam.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.customer.Customer;

import java.time.Duration;
import java.time.LocalDate;


import static pl.isa.alphateam.reservation.ReservationUtils.generateId;

@Component
@AllArgsConstructor
public class Reservation {
    private LocalDate startDate;
    private LocalDate endDate;
    private Customer customer;
    private Boat boat;
    private String reservationCode = "";

    public Reservation() {

    }

    public Reservation(LocalDate startDate, LocalDate endDate, Customer customer, Boat boat) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.boat = boat;
       this.reservationCode = generateId();
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

    @Override
    public String toString() {
        return "Reservation{" +
                "startDate='" + startDate + '\'' +
                ", endDate=" + endDate +
                ", customer=" + customer +
                ", boat='" + boat + '\'' +
                '}';
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
        long dayNo = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays()+1;
        return boat.getCostPerDay() * dayNo;
    }

    @JsonIgnore
    public String getReservationCode() {
        return reservationCode;
    }

}
