package com.example.reservationsystem.Business;

public enum ReservationType {
    
    POOL("POOL"), SAUNA("SAUNA"), GYM("GYM");

    private final String name;

    private ReservationType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
