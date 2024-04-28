package pl.isa.alphateam.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.isa.alphateam.boat.Boat;
import pl.isa.alphateam.controllers.CustomerController;
import pl.isa.alphateam.customer.Customer;
import pl.isa.alphateam.reservation.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.isa.alphateam.parser.JSONParserBoat.getListOfBoatsFromDatabase;
import static pl.isa.alphateam.parser.JSONParserReservation.getListOfReservationsFromDatabase;
import static pl.isa.alphateam.parser.JSONParserReservation.saveReservationInDatabase;
import static pl.isa.alphateam.reservation.ReservationUtils.getListOfDaysForPeriod;

public class BoatReservationService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private static final Map<String, Reservation> reservationCodeMap = new HashMap<>();

    public static Reservation rentBoatWithReservationCode(String reservationCode) {
        logger.info("inside Boat Reservation Service - rentBoatWithReservationCode");
        return reservationCodeMap.get(reservationCode);
    }
    public static Map<String, Reservation> showCodesAndReservations() {
        logger.info("inside Boat Reservation Service - rentBoatWithReservationCode");
        return reservationCodeMap;    }

    public static boolean rentBoatForCustomerReservationCodeRoute(Customer customer, Reservation reservation) {
        List<Reservation> reservationList = new ArrayList<>(getListOfReservationsFromDatabase());
        try {
            reservation.setCustomer(customer);
            reservationList.add(reservation);
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

    public static Reservation rentBoatForCustomer(LocalDate startDate, LocalDate endDate, Customer customer, int boatID) {
        Boat boat = getBoatBasedOnBoatID(boatID);
        List<Reservation> reservationList = new ArrayList<>(getListOfReservationsFromDatabase());
        Reservation reservation = new Reservation(startDate, endDate, customer, boat);
        reservationList.add(reservation);
        saveReservationInDatabase(reservationList);
        return reservation;
    }

    public static Boat getBoatBasedOnBoatID(int boatID) {
        List<Boat> boats = getListOfBoatsFromDatabase();
        return boats.stream().filter(b -> b.getBoatId() == boatID).toList().get(0);
    }

    public static List<LocalDate> getListOFDatesNAforBoatId(int boatId) {
        List<LocalDate> datesForBoat = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>(getListOfReservationsFromDatabase());
        for (Reservation reservation : reservationList) {
            if (reservation.getBoat().getBoatId() == boatId) {
                LocalDate startDate = reservation.getStartDate();
                LocalDate endDate = reservation.getEndDate();

                datesForBoat = getListOfDaysForPeriod(startDate, endDate);
            }
        }
        return datesForBoat;
    }

    public static List<Reservation> getListOfReservationsForCustomer(Customer customer) {
        List<Reservation> reservationListForCustomer = new ArrayList<>();
        List<Reservation> reservationList = new ArrayList<>(getListOfReservationsFromDatabase());
        for (Reservation reservation : reservationList) {

            if (reservation.getCustomer().getEmailAddress().equals(customer.getEmailAddress())) {
                reservationListForCustomer.add(reservation);
            }
        }
        return reservationListForCustomer;
    }


}












