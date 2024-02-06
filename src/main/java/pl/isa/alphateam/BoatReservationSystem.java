package pl.isa.alphateam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static pl.isa.alphateam.JSONParserBoat.getListOfBoatsFromDatabase;

import static pl.isa.alphateam.JSONParserReservation.saveReservationInDatabase;

public class BoatReservationSystem {
    private static final Map<String, Reservation> reservationCodeMap = new HashMap<>();
    private static final List<Reservation> reservations = new ArrayList<>();

    public static Reservation reserveBoatFor24hrs( int boatID, LocalDate startDate, LocalDate endDate) {
        Boat boat = getListOfBoatsFromDatabase().get(boatID);
        Reservation reservation = new Reservation(startDate, endDate, null, boat);
        String reservationCode = reservation.getReservationCode();
        reservationCodeMap.put(reservationCode, reservation);
        return reservation;
    }

    public static Reservation rentBoatWithReservationCode(String reservationCode) {
        return reservationCodeMap.get(reservationCode);
    }

    public static boolean rentBoatForCustomerReservationCodeRoute(Customer customer, Reservation reservation) {
        reservation.setCustomer(customer);
        return true;
    }

    public static Reservation rentBoatForCustomerNoReservationCode(LocalDate startDate, LocalDate endDate, Customer customer, int boatID) {
        List<Boat> boats = getListOfBoatsFromDatabase();
        Boat boat = boats.stream().filter(b -> b.getBoatId() == boatID).toList().get(0);
        Reservation reservation = new Reservation(startDate, endDate, customer, boat);
        reservations.add(reservation);
        saveReservationInDatabase(reservations);

        return reservation;
    }
}












