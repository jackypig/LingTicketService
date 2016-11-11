package com.walmart.homework.models;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 11/5/16
 * Time: 9:12 PM
 */
public class Seat {
    private SeatState seatState;
    private int row;
    private int column;

    /**
     * Constructor of Seat
     */
    public Seat(int row, int column){
        this.row = row;
        this.column = column;
        this.seatState = SeatState.AVAILABLE;
    }

    /**
     * Get seat state
     * @return SeatState
     */
    public SeatState getSeatState() {
        return seatState;
    }

    /**
     * Get seat id
     * @return
     */
    public String getId() {
        StringBuilder sb = new StringBuilder();
        return sb.append(Util.getCharFromInt(row)).append(column + 1).toString();
    }

    /**
     * Get seat row
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Get seat column
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     * Hold the seat
     */
    public void hold(){
        seatState = SeatState.HELD;
    }

    /**
     * Reserve the seat
     */
    public void reserve(){
        seatState = SeatState.RESERVED;
    }

    /**
     * Release the seat
     */
    public void release(){
        seatState = SeatState.AVAILABLE;
    }

}
