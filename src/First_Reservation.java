import java.util.List;
import java.time.format.DateTimeFormatter;

public class First_Reservation extends Reservation_Strategy {

    public First_Reservation(List<Reserve> reserves) {
        super(reserves);
    }

    void addReserve(Reserve reserve){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String start = reserve.getStart_schedule().format(formatter);
        String end = reserve.getEnd_schedule().format(formatter);
        
        boolean available = Reservation.getInstance().roomAvailability(reserve.getRoom(), start, end);
        
        if (!available) {
            reserve.notifyObservers("Conflito | Reserva de " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber() + " não efetuada");
            return;
        }
        this.reserves.add(reserve);
        reserve.notifyObservers("Reserva efetuada: " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber());
    }
    
}
