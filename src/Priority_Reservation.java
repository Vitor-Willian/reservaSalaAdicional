import java.util.List;
import java.time.format.DateTimeFormatter;

public class Priority_Reservation extends Reservation_Strategy {

    public Priority_Reservation(List<Reserve> reserves) {
        super(reserves);
    }

    private boolean hasPriority(Reserve newReserve, Reserve existingReserve) {
        if (newReserve.getUser().getRole().equals("Professor") && existingReserve.getUser().getRole().equals("Aluno")) {
            return true;
        }
        return false;
    }

    void addReserve(Reserve reserve){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String start = reserve.getStart_schedule().format(formatter);
        String end = reserve.getEnd_schedule().format(formatter);
        
        boolean available = Reservation.getInstance().roomAvailability(reserve.getRoom(), start, end);
        
        if (available) {
            this.reserves.add(reserve);
            reserve.notifyObservers("Reserva efetuada: " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber());
        } else {
            Reserve conflict = null;
            for (Reserve existingReserve : this.reserves) {
                if (existingReserve.getRoom().getRoomNumber() == reserve.getRoom().getRoomNumber()) {
                    if (existingReserve.getStart_schedule().isBefore(reserve.getEnd_schedule()) &&
                        reserve.getStart_schedule().isBefore(existingReserve.getEnd_schedule())) {
                        conflict = existingReserve;
                        break;
                    }
                }
            }
            
            if (conflict != null && hasPriority(reserve, conflict)) {
                conflict.notifyObservers("Reserva da sala " + conflict.getRoom().getRoomNumber() + " cancelada devido a prioridade");
                this.reserves.remove(conflict);
                reserve.notifyObservers("Reserva efetuada: " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber());
                this.reserves.add(reserve);
            } else {
                reserve.notifyObservers("Conflito | Reserva de " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber() + " não efetuada");
            }
        }
    }
    
}