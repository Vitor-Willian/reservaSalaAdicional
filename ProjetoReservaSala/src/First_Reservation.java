public class First_Reservation extends Reservation_Strategy {

    public First_Reservation() {
        super();
    }

    private boolean hasConflict(Reserve newReserve) {
        for (Reserve existingReserve : this.reserves) {
            if (existingReserve.getRoom().getRoomNumber() == newReserve.getRoom().getRoomNumber()) {
                if (existingReserve.getStart_schedule().isBefore(newReserve.getEnd_schedule()) &&
                    newReserve.getStart_schedule().isBefore(existingReserve.getEnd_schedule())) {
                    return true;
                }
            }
        }
        return false;
    }

    void addReserve(Reserve reserve){
        if (hasConflict(reserve)) {
            reserve.notifyObservers("Conflito | Reserva de " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber() + " não efetuada");
            return;
        }
        this.reserves.add(reserve);
        reserve.notifyObservers("Reserva efetuada: " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber());
    }
    
}
