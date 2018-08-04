package com.walmart.homework.models;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 8/1/18
 */
@Value
public class SeatHold {
    int id;
    @NonNull
    Set<Seat> seats;
    @NonNull
    Long createdTime;
    @NonNull
    String customerEmail;
}
