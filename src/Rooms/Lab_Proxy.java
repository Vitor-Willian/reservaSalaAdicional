package Rooms;

public class Lab_Proxy implements Room_Proxy {
    private Lab_Room room;

    public Lab_Proxy(Lab_Room room) {
        this.room = room;
    }
    
    @Override
    public boolean reservePermitted(String role) {
        return role.equals("Professor");
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
