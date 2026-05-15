package Rooms;

public class FactoryRoom {
    
    public static Room createRoom(String type, int roomNumber, int capacity) {
        if (type.equalsIgnoreCase("individual")) {
            // Cria a sala e depois "embrulha" ela com o Proxy antes de retornar
            Individual_Room individualRoom = new Individual_Room(roomNumber);
            return new Individual_Proxy(individualRoom);
            
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