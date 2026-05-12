package Rooms;

public class Lab_Room implements Room {
    private String roomType;
    private int roomNumber;

    public Lab_Room(int roomNumber) {
        this.roomType = "Laboratório";
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}
