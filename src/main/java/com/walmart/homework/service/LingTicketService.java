package com.walmart.homework.service;

import com.walmart.homework.models.*;

import java.util.*;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 11/5/16
 * Time: 9:15 PM
 */
public class LingTicketService implements TicketService {
    private static Venue venue;
    private Map<Integer, SeatHold> seatHoldMap = new HashMap<Integer, SeatHold>();
    private Map<String, Order> orderMap = new HashMap<String, Order>();
    private Queue<SeatHold> seatHolds = new LinkedList<SeatHold>();
    private int holdId = 1000;

    public LingTicketService (int rows, int seatsPerRow) {
        venue = Venue.getInstance(rows, seatsPerRow);
    }

    public Seat[][] showAllSeats() {
        return venue.getAllSeats();
    }

    public int numSeatsAvailable() {
        return venue.getAvailableSeats();
    }

    public synchronized SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        Set<Seat> seats = new LinkedHashSet<Seat>();
        int count = numSeats;
        for (int i=0; i<venue.getRows(); i++) {
            for (int j=0; j<venue.getNumberOfSeatsPerRow(); j++) {
                if (count == 0) {
                    break;
                }

                if (venue.getAllSeats()[i][j].getSeatState() == SeatState.AVAILABLE) {
                    seats.add(holdSeat(i, j));
                    count--;
                }
            }
        }

        SeatHold seatHold = new SeatHold(seats, holdId, new Date().getTime(), customerEmail);
        seatHoldMap.put(holdId, seatHold);
        seatHolds.offer(seatHold);
        holdId++;

        return seatHold;
    }

    public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
        SeatHold seatHold = seatHoldMap.get(seatHoldId);
        if (seatHold == null) {
            return null;
        }

        Set<Seat> holdSeats = seatHold.getSeats();
        Order order = new Order();
        order.setCustomerEmail(seatHold.getCustomerEmail());
        String confirmationCode = UUID.randomUUID().toString();
        order.setConfirmationCode(confirmationCode);
        order.setReservedSeats(holdSeats);

        for (Seat seat: holdSeats) {
            seat.reserve();
        }

        // Save order to map
        orderMap.put(confirmationCode, order);

        // Remove this seatHold
        seatHoldMap.remove(seatHoldId);

        return confirmationCode;
    }

    public Seat holdSeat(int row, int column) {
        return venue.holdSeat(row, column);
    }

    public Order getOrder(String code) {
        return orderMap.get(code);
    }

    public void refresh() {
        long current = new Date().getTime();
        boolean proceed = true;
        while (!seatHolds.isEmpty() && proceed) {
            SeatHold seatHold = seatHolds.peek();
            if (current - seatHold.getTimeOfHold() > 30000) {
                for (Seat seat: seatHold.getSeats()) {
                    venue.releaseSeat(seat);
                }
                seatHolds.remove(); // same as seatHolds.poll()
                seatHoldMap.remove(seatHold.getHoldId());
            } else {
                proceed = false;
            }
        }
    }
}
