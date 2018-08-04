package com.walmart.homework.models;

import java.util.Random;
import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 8/1/18
 */
public class Util {
    private static final String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final Random RAND = new Random();


    public static String generateSeatsString(Set<Seat> seats) {
        StringBuilder sb = new StringBuilder();
        for ( Seat seat: seats ) {
            sb.append( seat.getSeatId() ).append(",");
        }
        sb.replace( sb.length()-1, sb.length(), "");

        return sb.toString();
    }

    public static String generateId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(alphabet.charAt(RAND.nextInt(15)));
        }
        return sb.toString();
    }
}
