import java.time.LocalDate;
import java.time.Month;

public class bin {
    public static void main(String[] args) {

            LocalDate dob = LocalDate.of(1971, Month.MAY, 16);
            System.out.printf(
                    "%1$tB %1$td,  %1$tY is %2$s's birth day. Let's go and celebrate.",
                    dob, "Mike");
String startDateStr = "2024-01-01";
       LocalDate date =LocalDate.parse(startDateStr);
        System.out.println(date); System.out.printf(
                " %1$tY %1$tB %1$td is %2$s's birth day. Let's go and celebrate %1$tY %1$tb %1$td .",
                date, "Mike",date);
        LocalDate date2 = LocalDate.now();

        System.out.printf("Year: %s%n", date2.getYear());
        System.out.printf("Month: %s%n", date2.getMonthValue());
        System.out.printf("Day: %s%n", date2.getDayOfMonth());
    }
}
