package pl.isa.alphateam;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties
public class Boat {
    private String name;
    private int capacity;
    private double costPerDay;
    private boolean isAvailable;
    private String boatId;

    private LocalDate date;

    public Boat() {

    }

    public Boat(String name, int capacity, double costPerDay, boolean isAvailable, String boatId) {
        this.name = name;
        this.capacity = capacity;
        this.costPerDay = costPerDay;
        this.isAvailable = isAvailable;
        this.boatId = boatId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Boat{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", costPerDay=" + costPerDay +
                ", isAvailable=" + isAvailable +
                ", boatId='" + boatId + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getBoatId() {
        return boatId;
    }

    public void setBoatId(String boatId) {
        this.boatId = boatId;
    }

    @JsonProperty("date")
    public String getDateProperty() {
        return date.toString();
    }

    public void setDateProperty(String date) {
        this.date = LocalDate.parse(date);
    }

    @JsonIgnore
    public LocalDate getDate() {
        return date;
    }
}
