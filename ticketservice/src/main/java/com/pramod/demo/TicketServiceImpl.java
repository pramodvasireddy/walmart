package com.pramod.demo;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

    private final Map<Integer, Seat> seats;
    private final Map<Integer, SeatHold> seatHoldMap = new HashMap<>();

    public TicketServiceImpl(Map<Integer, Seat> seats) {
        this.seats = seats;
    }

    @Override
    public int numSeatsAvailable() {
        return this.collectionOfSeats().size();

    }

    @Override
    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {

        SeatHold seatHold = new SeatHold();
        List<Map.Entry<Integer, Seat>> entries = this.collectionOfSeats();
//        entries.forEach(integerSeatEntry -> {
//            System.out.print(integerSeatEntry.getKey() + "\t");
//        });

        int flag = 0;
        for (int x = 0; x < numSeats; x++) {
            for (int x2 = flag; x < entries.size(); x2++) {
                entries.get(x2).getValue().setHold(true);
                entries.get(x2).getValue().setShowStatus("H");
                flag++;
                seatHold.getNumSeatsToHold().add(entries.get(x2).getValue().getSeatNumber());
                break;
            }
        }

        seatHold.setSeatHoldId(this.seatHoldMap.size() + 1);
        seatHold.setCustomerEmail(customerEmail);
        this.seatHoldMap.put(seatHold.getSeatHoldId(), seatHold);
        new Thread(new Timer(Instant.now(), seatHold, this.seats)).start();
        return seatHold;
    }

    @Override
    public String reserveSeats(int seatHoldId, String customerEmail) {
        String confirmation_Code;
        if (this.seatHoldMap.get(seatHoldId) != null && this.seatHoldMap.get(seatHoldId).getCustomerEmail().equals(customerEmail)) {
            this.seatHoldMap.get(seatHoldId).getNumSeatsToHold().forEach(integer -> {
                this.seats.get(integer).setReserved(true);
                this.seats.get(integer).setShowStatus("R");
            });
            confirmation_Code = "You successfully reserved seats for seat hold id: " + seatHoldId;
        } else {
            confirmation_Code = String.format("Seat hold id : %s is not available", seatHoldId);
        }
        return confirmation_Code;
    }


    private List<Map.Entry<Integer, Seat>> collectionOfSeats() {
        return this.seats.entrySet().stream().filter(seatEntry -> !seatEntry.getValue().isHold() && !seatEntry.getValue().isReserved()).collect(Collectors.toList());
    }

}
