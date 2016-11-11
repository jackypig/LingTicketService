package com.walmart.homework.models;

import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 11/5/16
 * Time: 9:14 PM
 */
public class SeatHold {
    private int holdId;
    private Set<Seat> seats;
    private Long timeOfHold;
    private String customerEmail;

    public SeatHold () {

    }

    public SeatHold(Set<Seat> seats, int holdId, Long timeOfHold, String customerEmail){
        this.seats = seats;
        this.holdId = holdId;
        this.timeOfHold = timeOfHold;
        this.customerEmail = customerEmail;
    }

    public Long getTimeOfHold(){
        return timeOfHold;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public int getHoldId(){
        return holdId;
    }

    public void setHoldId(int holdId){
        this.holdId = holdId;
    }

    public void setTimeOfHold(Long timeOfHold) {
        this.timeOfHold = timeOfHold;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
