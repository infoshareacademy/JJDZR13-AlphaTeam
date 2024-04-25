package pl.isa.alphateam.reservation;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Data
@Component
public class FilterDates {
    private LocalDate startDate;
    private LocalDate endDate;
}
