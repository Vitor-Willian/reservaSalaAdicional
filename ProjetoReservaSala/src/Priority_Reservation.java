public class Priority_Reservation extends Reservation_Strategy {

    public Priority_Reservation() {
        super();
    }

    private Reserve hasConflict(Reserve newReserve) {
        for (Reserve existingReserve : this.reserves) {
            if (existingReserve.getRoom().getRoomNumber() == newReserve.getRoom().getRoomNumber()) {
                if (existingReserve.getStart_schedule().isBefore(newReserve.getEnd_schedule()) &&
                    newReserve.getStart_schedule().isBefore(existingReserve.getEnd_schedule())) {
                    return existingReserve;
                }
            }
        }
        return null;
    }
    
    private boolean hasPriority(Reserve newReserve, Reserve existingReserve) {
        if (newReserve.getUser().getRole().equals("Professor") && existingReserve.getUser().getRole().equals("Aluno")) {
            return true;
        }
        return false;
    }

    void addReserve(Reserve reserve){
        Reserve conflict = hasConflict(reserve);
        
        if (conflict == null) {
            this.reserves.add(reserve);
            reserve.notifyObservers("Reserva efetuada: " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber());
        } else {
            if (hasPriority(reserve, conflict)) {
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