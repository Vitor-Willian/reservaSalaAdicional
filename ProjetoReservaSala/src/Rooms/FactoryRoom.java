package Rooms;

public class FactoryRoom {
    
    public static Room createRoom(String type, int roomNumber, int capacity) {
        if (type.equalsIgnoreCase("individual")) {
            return new Individual_Room(roomNumber);
        } else if (type.equalsIgnoreCase("lab")) {
            return new Lab_Room(roomNumber);
        } else if (type.equalsIgnoreCase("grupo")) {
            return new Group_Room(roomNumber, capacity);
        }
        throw new IllegalArgumentException("Tipo de sala inválido: " + type);
    }
}
