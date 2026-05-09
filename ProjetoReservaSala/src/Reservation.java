import java.util.List;
import java.util.ArrayList;

public class Reservation {
    List<Reserve> reserves;

    public Reservation() {
        this.reserves = new ArrayList<>();
    }

    void addReserve(Reserve reserve){
        reserves.add(reserve);
    }
    void removeReserve(Reserve reserve){
        reserves.remove(reserve);
    }
    void updateReserve(Reserve oldReserve, Reserve newReserve){
        int index = reserves.indexOf(oldReserve);
        if (index != -1) {
            reserves.set(index, newReserve);
        }
        else {
            System.out.println("Reserva não encontrada: " + oldReserve);
        }
    }
    void listReserves(){
        for (Reserve reserve : reserves) {
            System.out.println(reserve.getUser().getName() + " reservou a sala " + reserve.getRoom().getRoomNumber() + " das " + reserve.getStart_schedule() + " às " + reserve.getEnd_schedule());
        }
    }
}
