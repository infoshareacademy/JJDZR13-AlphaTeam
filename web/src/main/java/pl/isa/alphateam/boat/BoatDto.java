package pl.isa.alphateam.boat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoatDto {
    private int boatId;
    private String name;
    private int capacity;
    private double costPerDay;
    private boolean isAvailable;
    private List<LocalDate> notAvailableDates;


}
