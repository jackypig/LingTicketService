package com.walmart.homework.models;

import lombok.Data;
import lombok.NonNull;

import static com.walmart.homework.models.SeatState.AVAILABLE;
import static com.walmart.homework.models.SeatState.HELD;
import static com.walmart.homework.models.SeatState.RESERVED;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 8/1/18
 */
@Data
public class Seat {
    @NonNull
    String seatId;
    @NonNull
    SeatState state;
    @NonNull
    int row;
    @NonNull
    int column;
    @NonNull
    int zone;

    /**
     * Hold the seat
     */
    public void hold(){
        this.state = HELD;
    }

    /**
     * Reserve the seat
     */
    public void reserve(){
        this.state = RESERVED;
    }

    /**
     * Release the seat
     */
    public void release(){
        this.state = AVAILABLE;
    }

}
