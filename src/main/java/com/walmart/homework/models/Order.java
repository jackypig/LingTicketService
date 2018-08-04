package com.walmart.homework.models;

import lombok.NonNull;
import lombok.ToString;
import lombok.Value;

import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 8/1/18
 */
@Value
@ToString
public class Order {
    @NonNull
    String customerEmail;
    @NonNull
    String confirmationCode;
    @NonNull
    Set<Seat> reservedSeats;
}
