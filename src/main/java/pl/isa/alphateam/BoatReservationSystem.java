package pl.isa.alphateam;


import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;

import static pl.isa.alphateam.JSONParserReservation.getListOfReservationsFromFakeDatabase;
import static pl.isa.alphateam.JSONParserReservation.saveReservationInDatabase;
import static pl.isa.alphateam.ReservationUtils.getListOfDaysForPeriod;

public class BoatReservationSystem {
    private static final Map<String, Reservation> reservationCodeMap = new HashMap<>();
    private static final List<Reservation> reservations = getListOfReservationsFromFakeDatabase();

    public static Reservation rentBoatWithReservationCode(String reservationCode) {
        return reservationCodeMap.get(reservationCode);
    }

    public static boolean rentBoatForCustomerReservationCodeRoute(Customer customer, Reservation reservation) {
        try {
            reservation.setCustomer(customer);
            reservations.add(reservation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Reservation reserveBoatFor24hrs(int boatID, LocalDate startDate, LocalDate endDate) {
        Boat boat = getBoatBasedOnBoatID(boatID);
        Reservation reservation = new Reservation(startDate, endDate, null, boat);
        String reservationCode = reservation.getReservationCode();
        reservationCodeMap.put(reservationCode, reservation);
        return reservation;
    }

    public static Reservation rentBoatForCustomerNoReservationCode(LocalDate startDate, LocalDate endDate, Customer customer, int boatID) {
        Boat boat = getBoatBasedOnBoatID(boatID);
        Reservation reservation = new Reservation(startDate, endDate, customer, boat);
        reservations.add(reservation);
        saveReservationInDatabase(reservations);
        return reservation;
    }

    private static Boat getBoatBasedOnBoatID(int boatID) {
        List<Boat> boats = getListOfBoatsFromDatabase();
        return boats.stream().filter(b -> b.getBoatId() == boatID).toList().get(0);
    }

    public static List<LocalDate> getListOFDatesNAforBoatId(int boatId) {
        List<LocalDate> datesForBoat = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getBoat().getBoatId() == boatId) {
                LocalDate startDate = reservation.getStartDate();
                LocalDate endDate = reservation.getEndDate();

                datesForBoat= getListOfDaysForPeriod(startDate, endDate);

            /*    long dayNo = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
                for (long i = 0; i <= dayNo; i++) {
                    datesForBoat.add(startDate.plusDays(i));
                }*/
            }
        }
        return datesForBoat;
    }

}












