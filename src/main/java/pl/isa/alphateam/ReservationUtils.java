package pl.isa.alphateam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationUtils {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String PREFIX = "Boat";
    private static int count = -1;
    public static String generateId()  {
        String postfix = String.valueOf(System.currentTimeMillis()%1000);
        count++;
        return PREFIX + postfix+count;
    }

/*    public static boolean enterYourReservationCode(Reservation reservation) {
        String userInput = scanner.next();
       if (!reservationCodeStartCountMap.containsValue(userInput)) {
            System.out.println("Incorrect code, try again");
            enterYourReservationCode(reservation);
        } else {
            reservationCodeStartCountMap.remove(reservation.getReservationCode());
            System.out.println("Your code was accepted and you successfully rented a boat");
            return true;
        }
        return false;
    }*/



}
