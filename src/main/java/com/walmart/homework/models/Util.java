package com.walmart.homework.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 11/6/16
 * Time: 3:05 PM
 */
public class Util {
    private static final Map<Integer, Character> INTEGER_CHARACTER_MAP = generateMap();

    public static Character getCharFromInt(int number) {
        return INTEGER_CHARACTER_MAP.get(number);
    }

    private static Map<Integer, Character> generateMap() {
        Map<Integer, Character> map = new HashMap<Integer, Character>();
        map.put(0, 'A');
        map.put(1, 'B');
        map.put(2, 'C');
        map.put(3, 'D');
        map.put(4, 'E');
        map.put(5, 'F');
        map.put(6, 'G');
        map.put(7, 'H');
        map.put(8, 'I');
        map.put(9, 'J');
        map.put(10, 'K');
        map.put(11, 'L');
        map.put(12, 'M');
        map.put(13, 'N');
        map.put(14, 'O');
        map.put(15, 'P');
        map.put(16, 'Q');
        map.put(17, 'R');
        map.put(18, 'S');
        map.put(19, 'T');
        map.put(20, 'U');
        map.put(21, 'V');
        map.put(22, 'W');
        map.put(23, 'X');
        map.put(24, 'Y');
        map.put(25, 'Z');

        return map;
    }

    public static String generateSeatsString(Set<Seat> seatSet) {
        StringBuilder seatsStringBuilder = new StringBuilder();
        seatsStringBuilder.append("[");
        for (Seat seat: seatSet) {
            seatsStringBuilder.append(getCharFromInt(seat.getRow())).append(seat.getColumn() + 1).append(" ");
        }
        String seatsString = seatsStringBuilder.substring(0, seatsStringBuilder.length() - 1);
        seatsString += "]";
        return seatsString;
    }
}
