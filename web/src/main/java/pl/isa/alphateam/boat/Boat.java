package pl.isa.alphateam.boat;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "boats")
public class Boat {
    @Id
    private int boatId;
    private String name;
    private int capacity;
    private double costPerDay;
    private boolean isAvailable;

    public Boat(String name, int capacity, double costPerDay, boolean isAvailable, int boatId) {
        this.name = name;
        this.capacity = capacity;
        this.costPerDay = costPerDay;
        this.isAvailable = isAvailable;
        this.boatId = boatId;
    }
    public String getName() {
        return name;
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

    public int getBoatId() {
        return boatId;
    }

    public void setBoatId(int boatId) {
        this.boatId = boatId;
    }
}
