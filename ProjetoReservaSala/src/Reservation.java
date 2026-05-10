import java.util.List;

public class Reservation {
    private Reservation_Strategy strategy;
    private static Reservation instance;

    private Reservation() {
        strategy = new First_Reservation();
    }

    public static Reservation getInstance() {
        if (instance == null) {
            instance = new Reservation();
        }
        return instance;
    }

    public void setStrategy(Reservation_Strategy strategy) {
        this.strategy = strategy;
    }

    void addReserve(Reserve reserve){
        strategy.addReserve(reserve);
    }

    void removeReserve(Reserve reserve){
        strategy.getReserves().remove(reserve);
        reserve.notifyObservers("Reserva Cancelada: " + reserve.getUser().getName() + " " + reserve.getRoom().getRoomNumber());
    }
    void updateReserve(Reserve oldReserve, Reserve newReserve){
        List<Reserve> reserves = strategy.getReserves();
        int index = reserves.indexOf(oldReserve);
        if (index != -1) {
            reserves.set(index, newReserve);
            newReserve.notifyObservers("Reserva Atualizada: " + newReserve.getUser().getName() + " " + newReserve.getRoom().getRoomNumber());
        }
        else {
            System.out.println("Reserva não encontrada: " + oldReserve);
        }
    }
    void listReserves(){
        for (Reserve reserve : strategy.getReserves()) {
            System.out.println(reserve.getUser().getName() + " reservou a sala " + reserve.getRoom().getRoomNumber() + " das " + reserve.getStart_schedule() + " às " + reserve.getEnd_schedule());
        }
    }
}
