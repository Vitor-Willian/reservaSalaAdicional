public class Group_Room implements Room {
    private int roomNumber;
    private int capacity;
    private boolean isOccupied;

    public Group_Room(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
}
