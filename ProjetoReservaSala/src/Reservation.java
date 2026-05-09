import java.util.List;
import java.util.ArrayList;

public class Reservation {
    List<Reserve> reserves;

    public Reservation() {
        this.reserves = new ArrayList<>();
    }

    private boolean hasConflict(Reserve newReserve) {
        for (Reserve existingReserve : reserves) {
            if (existingReserve.getRoom().getRoomNumber() == newReserve.getRoom().getRoomNumber()) {
                if (existingReserve.getStart_schedule().isBefore(newReserve.getEnd_schedule()) &&
                    newReserve.getStart_schedule().isBefore(existingReserve.getEnd_schedule())) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean addReserve(Reserve reserve){
        if (hasConflict(reserve)) {
            System.out.println("ERRO: Conflito");
            return false;
        }
        reserves.add(reserve);
        return true;
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
