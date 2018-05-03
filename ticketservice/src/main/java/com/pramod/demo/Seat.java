package com.pramod.demo;

public class Seat implements Comparable<Seat> {

    private int seatNumber;
    private boolean isReserved = false;
    private boolean isHold = false;
    private String showStatus;

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
        this.setShowStatus(String.valueOf(this.getSeatNumber()));
    }


    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }


    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public boolean isHold() {
        return isHold;
    }

    public void setHold(boolean hold) {
        isHold = hold;
    }

    @Override
    public int compareTo(Seat seat) {
        Integer integer = new Integer(this.getSeatNumber());
        return integer.compareTo(seat.getSeatNumber());
    }
}
