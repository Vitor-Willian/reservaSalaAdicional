package Rooms;

public class Individual_Room implements Room {
    private String roomType;
    private int roomNumber;

    public Individual_Room(int roomNumber) {
        this.roomType = "Sala Individual";
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
