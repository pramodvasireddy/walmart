package com.pramod.demo;

import java.io.IOException;
import java.util.Map;
import java.util.stream.IntStream;


public class Seats {

    private final Map<Integer, Seat> seats;
    private final TicketService ticketService;

    public Seats(TicketService ticketService, Map<Integer, Seat> seats) {
        this.ticketService = ticketService;
        this.seats = seats;
        initSeating(100);
    }

    public void reserveSeats(int seatHolderId, String customerEmail) throws IOException {
        String response = this.ticketService.reserveSeats(seatHolderId, customerEmail);
        System.out.println(response);
    }

    public void findAndHoldSeats(int numSeats, String customerEmail) {
        SeatHold seatHold = this.ticketService.findAndHoldSeats(numSeats, customerEmail);
        System.out.println("Your seat hold id is: "+seatHold.getSeatHoldId());
    }

    public void printSeating() {
        IntStream.range(0, seats.size()).forEach(value -> {
            if (this.seats.get(value + 1).getSeatNumber() == 1) {
                System.out.println("----------------STAGE----------------");
            }
            System.out.print(this.seats.get(value + 1).getShowStatus() + "\t");
            if (this.seats.get(value + 1).getSeatNumber() % 10 == 0) {
                System.out.println();
            }

        });

        System.out.println("The number of seats in the venue that are neither held nor reserved: " + this.ticketService.numSeatsAvailable());
        System.out.println();
    }


    private void initSeating(int totalSeats) {
        IntStream.range(0, totalSeats).forEach(value -> {
            Seat seat = new Seat();
            seat.setSeatNumber(value + 1);
            this.seats.put(value + 1, seat);
        });
    }
}
