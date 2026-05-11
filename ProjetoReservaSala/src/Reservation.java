import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Rooms.Room;

public class Reservation {
    private Reservation_Strategy strategy;
    private List<Reserve> reserves;
    private static Reservation instance;

    private Reservation() {
        this.reserves = new ArrayList<>();
        strategy = new First_Reservation(this.reserves);
    }

    public static synchronized Reservation getInstance() {
        if (instance == null) {
            instance = new Reservation();
        }
        return instance;
    }

    public void setStrategy(Reservation_Strategy strategy) {
        System.out.println("Nova estratégia de reserva aplicada: " + strategy.getClass().getSimpleName());
        this.strategy = strategy;
        this.strategy.setReserves(this.reserves);
    }

    void addReserve(Reserve reserve){
        strategy.addReserve(reserve);
    }

    void removeReserve(Reserve reserve){
        this.reserves.remove(reserve);
        reserve.notifyObservers("Reserva Cancelada: " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber());
    }
    void updateReserve(Reserve oldReserve, Reserve newReserve){
        int index = this.reserves.indexOf(oldReserve);
        if (index != -1) {
            reserves.set(index, newReserve);
            newReserve.notifyObservers("Reserva Atualizada: " + newReserve.getUser().getName() + " " + newReserve.getRoom().getRoomNumber());
        }
        else {
            System.out.println("Reserva não encontrada: " + oldReserve);
        }
    }
    void listReserves(){
        for (Reserve reserve : this.reserves) {
            System.out.println(reserve.getUser().getName() + " reservou a sala " + reserve.getRoom().getRoomNumber() + " das " + reserve.getStart_schedule() + " às " + reserve.getEnd_schedule());
        }
    }
    public List<Reserve> getReserves() {
        return this.reserves;
    }

    public boolean roomAvailability(Room room, String start_schedule, String end_schedule) {
      
        LocalDateTime start = LocalDateTime.parse(start_schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime end = LocalDateTime.parse(end_schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        for (Reserve reserve : this.reserves) {

            Room res_room = reserve.getRoom();
            LocalDateTime res_start = reserve.getStart_schedule();
            LocalDateTime res_end = reserve.getEnd_schedule();

            if (res_room.getRoomNumber() == room.getRoomNumber()) {
                if (res_start.compareTo(end) < 0 && res_end.compareTo(start) > 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
