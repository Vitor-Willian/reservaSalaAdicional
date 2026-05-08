public class Individual_Room implements Room {
    private int roomNumber;
    private boolean isOccupied;

    public Individual_Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
