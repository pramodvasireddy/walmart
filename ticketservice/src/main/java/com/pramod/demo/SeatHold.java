package com.pramod.demo;

import java.util.ArrayList;
import java.util.List;

public class SeatHold {

    private List<Integer> numSeatsToHold = new ArrayList<>();
    private int seatHoldId;
    private String customerEmail;

    public List<Integer> getNumSeatsToHold() {
        return numSeatsToHold;
    }

    public void setNumSeatsToHold(List<Integer> numSeatsToHold) {
        this.numSeatsToHold = numSeatsToHold;
    }

    public int getSeatHoldId() {
        return seatHoldId;
    }

    public void setSeatHoldId(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
