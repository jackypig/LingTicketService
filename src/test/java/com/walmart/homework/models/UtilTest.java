package com.walmart.homework.models;

import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Ling on 8/4/18.
 */
public class UtilTest {
    private Set<Seat> seatSet;

    @Before
    public void setup() {
        seatSet = ImmutableSet.of(
                Seat.builder().seatId( "0-1" ).row(0).column(1).state(SeatState.AVAILABLE).build(),
                Seat.builder().seatId( "0-2" ).row(0).column(2).state(SeatState.AVAILABLE).build(),
                Seat.builder().seatId( "0-3" ).row(0).column(3).state(SeatState.AVAILABLE).build()
        );
    }

    @Test
    public void generateSeatIds() {
        Assert.assertEquals( "0-1,0-2,0-3", Util.generateSeatsString( seatSet ) );
    }
}
