package com.walmart.homework;

import com.walmart.homework.models.Seat;
import com.walmart.homework.models.SeatHold;
import com.walmart.homework.models.SeatState;
import com.walmart.homework.models.Util;
import com.walmart.homework.service.LingTicketService;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 11/6/16
 * Time: 3:29 PM
 */
public class TicketServiceApplication {
    public static void main(String[] args) {

        System.out.println("*****  Welcome to the Ling's ticket service for Walmart. *****");
        System.out.println("Please enter the number of rows in the theater:");
        Scanner rowScanner = new Scanner(System.in);
        int row = rowScanner.nextInt();
        System.out.println("Please enter the number of seats per row in the theater:");
        Scanner colScanner = new Scanner(System.in);
        int col = colScanner.nextInt();

        LingTicketService lingTicketService = new LingTicketService(row, col);
        displayAllSeats(lingTicketService.showAllSeats());

        loop: while (true) {
            lingTicketService.refresh();
            System.out.println("");
            System.out.println("Please choose from the choices below.");
            System.out.println("1. Show all seats information.");
            System.out.println("2. Show the number of all available seats.");
            System.out.println("3. Hold seats.");
            System.out.println("4. Reserve seats.");
            System.out.println("5. Show reserved seats.");
            System.out.println("6. Exit the program.");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            int input;
            try{
                input = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please enter an integer value within the range.");
                break;
            }


            switch (input) {
                case (1): {
                    displayAllSeats(lingTicketService.showAllSeats());
                    break;
                }

                case (2): {
                    System.out.println("Number of available seats: " + lingTicketService.numSeatsAvailable());
                    break;
                }

                case (3): {
                    System.out.println("Please enter your email address:");
                    Scanner scanner1 = new Scanner(System.in);
                    String email;
                    try {
                        email = scanner1.next();
                        if (!email.contains("@")) {  // TODO - Use more accurate email check method
                            System.out.println("Please enter valid email.");
                            break;
                        }

                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Please enter a valid email.");
                        break;
                    }

                    System.out.println("Please select number of seats to hold:");
                    Scanner scanner2 = new Scanner(System.in);
                    Integer numberOfSeats;
                    try {
                        numberOfSeats = scanner2.nextInt();
                        if (numberOfSeats > lingTicketService.numSeatsAvailable()) {
                            System.out.println("Sorry, not enough seats to hold for you.");
                            break;
                        }

                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Please enter a valid row.");
                        break;
                    }

                    SeatHold seatHold = lingTicketService.findAndHoldSeats(numberOfSeats, email);
                    if (seatHold != null) {
                        System.out.println("You have successfully held " +
                                Util.generateSeatsString(seatHold.getSeats()) + ", your seat hold ID is " + seatHold.getHoldId() +
                                ".It will only hold for you for 30 seconds, please reserve before it expires.");

                    } else {
                        System.out.println("Oops! Something went wrong, please try again.");
                    }

                    break;
                }


                case (4): {
                    System.out.println("Please enter your seat hold ID:");
                    Scanner scanner1 = new Scanner(System.in);
                    Integer holdId;
                    try {
                        holdId = scanner1.nextInt();

                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Please enter a valid email.");
                        break;
                    }

                    String confirmationCode = lingTicketService.reserveSeats(holdId, "");

                    if (confirmationCode != null) {
                        Set<Seat> seats = lingTicketService.getOrder(confirmationCode).getReservedSeats();
                        System.out.println("Congratulations! You have successfully reserved " +
                                Util.generateSeatsString(seats) + ", your confirmation code is " + confirmationCode + ". Enjoy the show!" );

                    } else {
                        System.out.println("Sorry, please submit hold request first or your seat hold request has expired, please try again.");
                    }

                    break ;
                }

                case (5): {
                    System.out.println("Please enter your confirmation code:");
                    Scanner scanner5 = new Scanner(System.in);
                    String code = scanner5.next();
                    Set<Seat> seats = lingTicketService.getOrder(code).getReservedSeats();

                    if (seats != null) {
                        StringBuilder sb = new StringBuilder("Your reserved seats are: ");
                        for (Seat seat: seats) {
                            sb.append(seat.getId()).append(" ");
                        }
                        sb.append(". Enjoy your show!");
                        System.out.println(sb.toString());

                    } else {
                        System.out.println("Sorry, we can't find your order record!");
                    }

                    break ;
                }

                case (6): {
                    System.exit(0);
                }

                default: {
                    System.out.println("Wrong input. Please try again between the given values.");
                }

            }
            // ...
        }

    }

    public static void displayAllSeats(Seat[][] allSeats) {
        System.out.println();
        StringBuilder labelRow = new StringBuilder("   ");
        for (int j=0; j<allSeats[0].length; j++) {
            labelRow.append(j+1).append(" ");
        }
        System.out.println(labelRow.toString());
        System.out.println();

        for (int i=0; i<allSeats.length; i++) {
            char rowChar = Util.getCharFromInt(i);
            StringBuilder sb = new StringBuilder();
            sb.append(rowChar).append("  ");
            for (int j=0; j<allSeats[0].length; j++) {
                SeatState state = allSeats[i][j].getSeatState();
                if (state.equals(SeatState.AVAILABLE)) {
                    sb.append("O").append(" ");

                } else if (state.equals(SeatState.HELD)) {
                    sb.append("-").append(" ");

                } else {
                    sb.append("X").append(" ");
                }
            }
            System.out.println(sb.toString());
        }

        System.out.println();
        System.out.println("O: Available   -: Held   X: Reserved");
    }
}
