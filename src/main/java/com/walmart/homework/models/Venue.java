package com.walmart.homework.models;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 11/5/16
 * Time: 9:18 PM
 */
public class Venue {
    private static Venue instance;
    private int rows;
    private int numberOfSeatsPerRow;
    private int availableSeats;
    private Seat[][] allSeats;

    private Venue (int rows, int numberOfSeatsPerRow) {
        this.rows = rows;
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
        this.allSeats = new Seat[rows][numberOfSeatsPerRow];
        this.availableSeats = rows * numberOfSeatsPerRow;
        for(int i = 0; i < allSeats.length; ++i){
            for (int j = 0; j < allSeats[0].length; j++){
                allSeats[i][j] = new Seat(i,j);
            }
        }
    }

    public static synchronized Venue getInstance(int rows, int numberOfSeatsPerRow){
        if(instance == null){
            instance = new Venue(rows, numberOfSeatsPerRow);
        }
        return instance;
    }

    public Seat holdSeat(int row, int column) {
        Seat seat = allSeats[row][column];
        seat.hold();
        allSeats[seat.getRow()][seat.getColumn()] = seat;
        this.availableSeats -= 1;
        return seat;
    }

    public void reserveSeat(Seat seat){
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
     * Get number of seats per row
     * @return
     */
    public int getNumberOfSeatsPerRow() {
        return numberOfSeatsPerRow;
    }


    /**
     * Get number of rows
     * @return
     */
    public int getRows() {
        return rows;
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

    public void setAvailableSeats(int availableSeats){
        this.availableSeats = availableSeats;
    }

    public void setAllSeats(Seat[][] allSeats){
        this.allSeats = allSeats;
    }

}
