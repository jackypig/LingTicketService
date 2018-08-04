package com.walmart.homework.models;

import com.google.common.collect.ImmutableMap;

import java.util.*;

import static com.walmart.homework.models.SeatState.AVAILABLE;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 8/1/18
 */
public class Venue {
    private static Venue instance;
    private int availableSeats;
    private Seat[][] allSeats;
    private Map<Integer, Set<Seat>> seatsByZone;

    private Venue (int rows, int cols) {
        Set<Seat> seats1 = new LinkedHashSet<>();
        Set<Seat> seats2 = new LinkedHashSet<>();
        Set<Seat> seats3 = new LinkedHashSet<>();
        Set<Seat> seats4 = new LinkedHashSet<>();
        this.availableSeats = rows * cols;
        this.allSeats = new Seat[rows][cols];
        for( int i = 0; i < this.allSeats.length; i++ ){
            for ( int j = 0; j < this.allSeats[0].length; j++ ){
                boolean rightBound = this.allSeats[0].length % 3 == 0 ?
                        j >= 2 * this.allSeats[0].length / 3 : j > 2 * this.allSeats[0].length / 3;
                boolean leftBound = j < this.allSeats[0].length / 3;

                if ( i < this.allSeats.length / 2 ) {
                    if ( leftBound || rightBound ) {
                        this.allSeats[i][j] = new Seat( i + "-" + j, AVAILABLE, i, j, 2 );
                        seats2.add( this.allSeats[i][j] );

                    } else {
                        this.allSeats[i][j] = new Seat( i + "-" + j, AVAILABLE, i, j, 1 );
                        seats1.add( this.allSeats[i][j] );
                    }

                } else {
                    if ( leftBound || rightBound ) {
                        this.allSeats[i][j] = new Seat( i + "-" + j, AVAILABLE, i, j, 4 );
                        seats4.add( this.allSeats[i][j] );

                    } else {
                        this.allSeats[i][j] = new Seat( i + "-" + j, AVAILABLE, i, j, 3 );
                        seats3.add( this.allSeats[i][j] );
                    }
                }
            }
        }

        this.seatsByZone = ImmutableMap.of(1, seats1, 2, seats2, 3, seats3, 4, seats4);
    }

    public static synchronized Venue getInstance(int rows, int numberOfSeatsPerRow){
        if(instance == null){
            instance = new Venue(rows, numberOfSeatsPerRow);
        }
        return instance;
    }

    public void holdSeat( Seat seat ) {
        seat.hold();
        allSeats[seat.getRow()][seat.getColumn()] = seat;
        this.availableSeats -= 1;
    }

    public void reserveSeat( Seat seat ){
        seat.reserve();
        allSeats[seat.getRow()][seat.getColumn()] = seat;
    }

    /**
     * Releases the seat
     *
     * @param seat seat which is to be released
     */
    public void releaseSeat(Seat seat){
        seat.release();
        allSeats[seat.getRow()][seat.getColumn()] = seat;
        this.availableSeats += 1;
    }

    /**
     * Get number of available seats
     * @return
     */
    public int getAvailableSeats() {
        return availableSeats;
    }


    /**
     * Get all seats information
     * @return
     */
    public Seat[][] getAllSeats() {
        return allSeats;
    }

    public Set<Seat> getSeatByZone( int zone ) {
        return seatsByZone.get( zone );
    }
}
