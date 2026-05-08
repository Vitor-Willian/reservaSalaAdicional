package Rooms;

public class Lab_Room implements Room {
    private String roomType;
    private int roomNumber;
    private boolean isOccupied;

    public Lab_Room(int roomNumber) {
        this.roomType = "Laboratório";
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
