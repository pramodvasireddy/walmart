package com.pramod.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * The main class to execute the program
 */
public class Main {

    private static final String OPTIONS = "Please select options: \n" +
            " 1 : Show Seating (Reserved/Held/Available) \n" +
            " 2 : Hold Seat \n" +
            " 3 : Reserve Seat \n" +
            " 4 : exit";

    

    private static final String SEAT_HOLD_ID = "You selected option %s, please enter seat holder id:";
    private static final String NUMBER_OF_SEATS = "You selected option %s, please enter number of seats you want to find and hold";
    private static final String CUSTOMER_EMAIL = "You selected option %s, please enter email:";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) {


        Map<Integer, Seat> seatsMap = new HashMap<>();

        TicketService ticketService = new TicketServiceImpl(seatsMap);

        Seats seats = new Seats(ticketService, seatsMap);

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.println(OPTIONS);
                System.out.print("> ");
                int input = Integer.parseInt(bufferReader.readLine());

                switch (input) {
                    case 1:
                        seats.printSeating();
                        break;
                    case 2:
                        System.out.println(String.format(NUMBER_OF_SEATS, input));
                        System.out.print("> ");
                        int numSeats = Integer.parseInt(bufferReader.readLine());
                        System.out.println(String.format(CUSTOMER_EMAIL, input));
                        System.out.print("> ");
                        String customerEmail1 = bufferReader.readLine();
                        seats.findAndHoldSeats(numSeats, customerEmail1);
                        break;
                    case 3:
                        try {
                            System.out.println(String.format(SEAT_HOLD_ID, input));
                            System.out.print("> ");
                            int seatNum = Integer.parseInt(bufferReader.readLine());
                            System.out.println(String.format(CUSTOMER_EMAIL, input));
                            System.out.print("> ");
                            String customerEmail2 = bufferReader.readLine();
                            seats.reserveSeats(seatNum, customerEmail2);
                        } catch (Exception ex) {
                            System.out.println(String.format("---------- Wrong input %s ---------", ex.getMessage()));
                            continue;
                        }

                        break;
                    case 4:
                        System.out.println("Exiting....");
                        return;
                    default:
                        System.out.println("You entered wrong value! \n" + OPTIONS);
                        continue;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }
}
