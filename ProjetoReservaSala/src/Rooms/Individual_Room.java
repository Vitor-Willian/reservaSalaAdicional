package Rooms;

public class Individual_Room implements Room {
    private String roomType;
    private int roomNumber;
    private boolean isOccupied;

    public Individual_Room(int roomNumber) {
        this.roomType = "Sala Individual";
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
