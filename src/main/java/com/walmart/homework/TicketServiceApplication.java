package com.walmart.homework;

import com.walmart.homework.models.Seat;
import com.walmart.homework.models.SeatHold;
import com.walmart.homework.models.SeatState;
import com.walmart.homework.models.Util;
import com.walmart.homework.service.LingTicketService;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Author: Ling Hung
 * Project: walmart-ticket-service
 * Date: 8/1/18
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
        lingTicketService.displayAllSeats();

        loop: while (true) {
            lingTicketService.refresh();
            System.out.println("");
            System.out.println("Please choose from the choices below.");
            System.out.println("1. Show me all seats information.");
            System.out.println("2. Show me the number of available seats.");
            System.out.println("3. I want to hold seats.");
            System.out.println("4. I want to reserve my seats.");
            System.out.println("5. I forget my reserved seats.");
            System.out.println("6. Leave.");
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
                    lingTicketService.displayAllSeats();
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
                        if (!EmailValidator.getInstance().isValid(email)) {
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
                            System.out.format(
                                    "Sorry, not enough seats to hold for you. There are only %d seats available.",
                                    lingTicketService.numSeatsAvailable() );
                            break;
                        }

                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Please enter a valid row.");
                        break;
                    }

                    SeatHold seatHold = lingTicketService.findAndHoldSeats(numberOfSeats, email);
                    if (seatHold != null) {
                        System.out.println("You have successfully held seats " +
                                Util.generateSeatsString( seatHold.getSeats() ) +
                                "as shown below, your seat hold ID is " + seatHold.getId() +
                                ".\n It will hold for you for " + LingTicketService.EXPIRED_PERIOD / 1000 + " seconds, please reserve before it expires.");
                        lingTicketService.displayReservedSeats( seatHold.getSeats() );

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
                        System.out.println("Please enter a valid seat hold ID (should be a number).");
                        break;
                    }

                    String confirmationCode = lingTicketService.reserveSeats(holdId, "");

                    if (confirmationCode != null) {
                        Set<Seat> seats = lingTicketService.getOrder(confirmationCode).getReservedSeats();
                        System.out.println("Congratulations! You have successfully reserved " +
                                Util.generateSeatsString(seats) + "\n, your confirmation code is " + confirmationCode + ". Enjoy the show!" );

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
                        System.out.println( "Your reserved seats are: \n" +
                                Util.generateSeatsString( seats ) + "\n" +
                                "as shown below. Enjoy your show!\n" );
                        lingTicketService.displayReservedSeats( seats );

                    } else {
                        System.out.println("Sorry, we can't find your order!");
                    }

                    break ;
                }

                case (6): {
                    System.out.println("Bye~ Hope to see you again!");
                    System.exit(0);
                }

                default: {
                    System.out.println("Wrong input. Please try again between the given values.");
                }

            }
        }

    }
}
