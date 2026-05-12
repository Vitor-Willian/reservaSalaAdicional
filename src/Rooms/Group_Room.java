package Rooms;

public class Group_Room implements Room {
    private String roomType;
    private int roomNumber;
    private int capacity;

    public Group_Room(int roomNumber, int capacity) {
        this.roomType = "Sala de Grupo";
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public String getRoomType() {
        return roomType;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }
}
