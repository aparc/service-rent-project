package ru.aparc.domain;

public enum RentType {

    ROOM("Room"),
    ONE_ROOM_FLAT("OneRoomFlat"),
    TWO_ROOM_FLAT("TwoRoomFlat");

    String rentType;

    RentType(String rentType) {
        this.rentType = rentType;
    }

    public String getRentType() {
        return rentType;
    }
}
