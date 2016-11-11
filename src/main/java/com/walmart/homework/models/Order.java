package com.walmart.homework.models;

import java.util.List;
import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 11/6/16
 * Time: 2:28 PM
 */
public class Order {
    private String customerEmail;
    private String confirmationCode;
    private Set<Seat> reservedSeats;

    public Set<Seat> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Set<Seat> seats) {
        this.reservedSeats = seats;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getConfirmationCode () {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
