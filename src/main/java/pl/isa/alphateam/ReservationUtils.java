package pl.isa.alphateam;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class ReservationUtils {
    private static final String PREFIX = "Boat";
    private static int count = -1;
    public static String generateId()  {
        String postfix = String.valueOf(System.currentTimeMillis()%1000);
        count++;
        return PREFIX + postfix+count;
    }

    public static List<LocalDate> getListOfDaysForPeriod(LocalDate startDate, LocalDate endDate) {
        long daysNo = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        List<LocalDate> listOfDaysInPeriod = new ArrayList<>();
        for (long i = 0; i <= daysNo; i++) {
            listOfDaysInPeriod.add(startDate.plusDays(i));
        }
        return listOfDaysInPeriod;
    }


}
