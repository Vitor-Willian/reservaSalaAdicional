package Rooms;

public class Individual_Proxy implements Room_Proxy {
    private Individual_Room room;

    public Individual_Proxy(Individual_Room room) {
        this.room = room;
    }
}