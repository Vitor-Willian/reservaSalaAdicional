package Rooms;

public class Individual_Proxy implements Room_Proxy {
    private Individual_Room room;

    public Individual_Proxy(Individual_Room room) {
        this.room = room;
    }
    @Override
    public boolean reservePermitted(String role) {
        // Retorna true apenas se o papel for diferente de "Professor" (ou seja, "Aluno")
        return !role.equals("Professor");
    }
        
    @Override
    public int getRoomNumber() {
        return room.getRoomNumber();
    }

    @Override
    public String getRoomType() {
        return room.getRoomType();
    }
}
