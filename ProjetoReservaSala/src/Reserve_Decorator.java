import java.time.LocalDateTime;
import Rooms.*;

public abstract class Reserve_Decorator implements Subject {
    protected Reserve reserve;

    public Reserve_Decorator(Reserve reserve) {
        this.reserve = reserve;
    }

    @Override
    public void addObserver(Observer observer) {
        reserve.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        reserve.removeObserver(observer);
    }

    @Override
    public void notifyObservers(String message) {
        reserve.notifyObservers(message);
    }

    public User getUser() {
        return reserve.getUser();
    }

    public LocalDateTime getStart_schedule() {
        return reserve.getStart_schedule();
    }

    public LocalDateTime getEnd_schedule() {
        return reserve.getEnd_schedule();
    }

    public Room getRoom() {
        return reserve.getRoom();
    }
    
}
