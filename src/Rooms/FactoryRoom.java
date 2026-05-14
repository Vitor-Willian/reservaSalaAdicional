package Rooms;

public class FactoryRoom {
    
    public static Room createRoom(String type, int roomNumber, int capacity) {
        if (type.equalsIgnoreCase("individual")) {
            return new Individual_Room(roomNumber);
        } else if (type.equalsIgnoreCase("lab")) {
            Lab_Room labRoom = new Lab_Room(roomNumber);
            return new Lab_Proxy(labRoom);
        } else if (type.equalsIgnoreCase("grupo")) {
            return new Group_Room(roomNumber, capacity);
        } else {
             System.out.println("Tipo de sala inválido: " + type);
             return null;
        }
    }
}
