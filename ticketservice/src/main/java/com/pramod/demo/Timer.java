package com.pramod.demo;

import java.time.Instant;
import java.util.Map;

public class Timer implements Runnable {

    private Instant instant;
    private SeatHold seatHold;
    private Map<Integer, Seat> seats;

    public Timer(Instant instant, SeatHold seatHold, Map<Integer, Seat> seats) {
        this.instant = instant;
        this.seatHold = seatHold;
        this.seats = seats;
    }

    @Override
    public void run() {
        while (true) {
            if (Instant.now().getEpochSecond() - this.instant.getEpochSecond() > 20) {
                this.seatHold.getNumSeatsToHold().forEach(integer -> {
                    if (!this.seats.get(integer).isReserved()) {
                        this.seats.get(integer).setHold(false);
                        this.seats.get(integer).setReserved(false);
                        this.seats.get(integer).setShowStatus(String.valueOf(this.seats.get(integer).getSeatNumber()));
                    }
                });
                break;
            }
        }
    }
}
