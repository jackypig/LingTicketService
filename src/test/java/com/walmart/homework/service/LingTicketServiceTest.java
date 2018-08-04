package com.walmart.homework.service;

import com.google.common.collect.ImmutableSet;
import com.walmart.homework.models.Seat;
import com.walmart.homework.models.SeatHold;
import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Ling on 8/4/18.
 */
public class LingTicketServiceTest {
    private static final String TEST_EMAIL = "test@walmart.com";

    @Test
    public void assignBestSeats() {
        LingTicketService lingTicketService = new LingTicketService( 10, 10 );
        Set<Seat> seats = lingTicketService.findBestSeats(4);
        Set<String> targetSeatIds = ImmutableSet.of("0-3", "0-4", "0-5", "0-6");
        seats.forEach( seat -> assertTrue( targetSeatIds.contains( seat.getSeatId() )) );
    }

    @Test
    public void holdSeats() {
        LingTicketService lingTicketService = new LingTicketService( 10, 10 );
        SeatHold seatHold = lingTicketService.findAndHoldSeats( 4, TEST_EMAIL);
        assertEquals(96, lingTicketService.numSeatsAvailable() );
        lingTicketService.reserveSeats( seatHold.getId(), TEST_EMAIL);
        assertEquals(96, lingTicketService.numSeatsAvailable() );
    }

    @Test
    public void releaseSeats() throws InterruptedException {
        LingTicketService lingTicketService = new LingTicketService( 10, 10, 1 );
        SeatHold seatHold = lingTicketService.findAndHoldSeats( 4, TEST_EMAIL);
        assertEquals(96, lingTicketService.numSeatsAvailable() );

        Thread.sleep( 1000 );

        lingTicketService.refresh();
        assertNull( "seat hold record should be already removed", lingTicketService.reserveSeats( seatHold.getId(), TEST_EMAIL ) );
        assertEquals(100, lingTicketService.numSeatsAvailable() );
    }

    @Test
    public void reserveSeats() {
        LingTicketService lingTicketService = new LingTicketService( 10, 10, 1 );
        SeatHold seatHold = lingTicketService.findAndHoldSeats( 4, TEST_EMAIL);
        assertEquals(96, lingTicketService.numSeatsAvailable() );
        assertNotNull( "should return a confirmation code", lingTicketService.reserveSeats( seatHold.getId(), TEST_EMAIL ) );
        assertEquals(96, lingTicketService.numSeatsAvailable() );
    }
}
