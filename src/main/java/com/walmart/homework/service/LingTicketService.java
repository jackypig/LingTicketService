
package com.walmart.homework.service;

import com.google.common.annotations.VisibleForTesting;
import com.walmart.homework.models.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.walmart.homework.models.SeatState.AVAILABLE;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 8/1/18
 */
public class LingTicketService implements TicketService {
    public static final int EXPIRED_PERIOD = 15000;  // Milliseconds
    private Venue venue;
    private Map<Integer, SeatHold> seatHoldMap;
    private Map<String, Order> orderMap;
    private AtomicInteger holdId;
    private int expiredPeriod;

    @VisibleForTesting
    LingTicketService (int rows, int cols, int expiredPeriod) {
        this.venue = new Venue(rows, cols);
        this.seatHoldMap = new HashMap<>();
        this.orderMap = new HashMap<>();
        this.holdId = new AtomicInteger(0);
        this.expiredPeriod = expiredPeriod;
    }

    public LingTicketService( int rows, int cols ) {
        this( rows, cols, EXPIRED_PERIOD );
    }

    public int numSeatsAvailable() {
        return venue.getAvailableSeats();
    }

    public synchronized SeatHold findAndHoldSeats(int numSeats, String email) {
        Set<Seat> seatsToHold = findBestSeats( numSeats );
        int seatHoldId = holdId.getAndIncrement();
        SeatHold seatHold = new SeatHold( seatHoldId, seatsToHold, new Date().getTime(), email );

        seatHoldMap.put(seatHoldId, seatHold);

        return seatHold;
    }

    @VisibleForTesting
    Set<Seat> findBestSeats( int numSeats ) {
        Set<Seat> seatsToHold = new HashSet<>();

        for ( int i=1; i<=4; i++ ) {
            for ( Seat seat: venue.getSeatByZone(i) ) {
                if (numSeats == 0) {
                    return seatsToHold;
                }

                if (seat.getState() == AVAILABLE) {
                    venue.holdSeat( seat );
                    seatsToHold.add( seat );
                    numSeats--;
                }
            }
        }

        return seatsToHold;
    }

    public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
        SeatHold seatHold = seatHoldMap.get( seatHoldId );
        if (seatHold == null) {
            return null;
        }

        Set<Seat> holdSeats = seatHold.getSeats();
        String confirmationCode = Util.generateId();

        // If code exists, regenerate another one
        while( orderMap.containsKey( confirmationCode ) ) {
            confirmationCode = Util.generateId();
        }

        Order order = new Order( seatHold.getCustomerEmail(), confirmationCode, holdSeats );

        for (Seat seat: holdSeats) {
            venue.reserveSeat( seat );
        }

        // Save order to map
        orderMap.put(confirmationCode, order);

        // Remove this seatHold
        seatHoldMap.remove(seatHoldId);

        return confirmationCode;
    }

    public Order getOrder(String code) {
        return orderMap.get(code);
    }

    public void refresh() {
        long current = new Date().getTime();
        for ( SeatHold seatHold: seatHoldMap.values() ) {
            // Release seats if expired
            if (current - seatHold.getCreatedTime() > expiredPeriod) {
                for (Seat seat: seatHold.getSeats()) {
                    venue.releaseSeat(seat);
                }
                seatHoldMap.remove( seatHold.getId() );
            }
        }
    }

    public void displayAllSeats() {
        Seat[][] allSeats = venue.getAllSeats();
        displayStage( allSeats );

        for (int i=0; i<allSeats.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<allSeats[0].length; j++) {
                Seat seat = allSeats[i][j];
                if (seat.getState() == SeatState.AVAILABLE) {
                    sb.append("O").append(" ");

                } else if (seat.getState() == SeatState.HELD) {
                    sb.append("-").append(" ");

                } else {
                    sb.append("X").append(" ");
                }
            }
            System.out.println(sb.toString());
        }

        System.out.println();
        System.out.println("O: Available  -: Held  X: Reserved");
    }

    public void displayReservedSeats( Set<Seat> seats ) {
        Seat[][] allSeats = venue.getAllSeats();
        displayStage( allSeats );
        Set<String> seatsIds = seats.stream().map( Seat::getSeatId ).collect(Collectors.toSet());

        for (int i=0; i<allSeats.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<allSeats[0].length; j++) {
                Seat seat = allSeats[i][j];

                if ( seatsIds.contains( seat.getSeatId() ) ) {
                    sb.append("X").append(" ");

                } else {
                    sb.append("-").append(" ");
                }
            }
            System.out.println(sb.toString());
        }

        System.out.println();
        System.out.println("X: Your seats   -: Other seats");
    }

    private void displayStage( Seat[][] allSeats ) {
        StringBuilder stageRow = new StringBuilder();
        StringBuilder dividerRow = new StringBuilder();
        int start = 0;
        if ( allSeats[0].length > 3 ) {
            while ( start < allSeats[0].length ) {
                if ( start == allSeats[0].length - 3 ) {
                    stageRow.append("Stage");
                    start += 6;

                } else {
                    stageRow.append(" ");
                    start++;
                }
            }
        }

        for (int j=0; j<allSeats[0].length * 2; j++) {
            dividerRow.append( "=" );
        }
        System.out.println(stageRow.toString());
        System.out.println(dividerRow.toString());
    }
}
