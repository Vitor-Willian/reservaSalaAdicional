import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Rooms.*;

public class Reserve implements Subject{
    private User user;
    private LocalDateTime start_schedule, end_schedule;
    private Room room;
    private List<Observer> observers = new ArrayList<>();

    public Reserve(User user, String start_schedule, String end_schedule, Room room) throws Exception {
        addObserver(user);
        
        // Verificação existente do Laboratório
        if(room instanceof Lab_Proxy) {
            if(!((Lab_Proxy) room).reservePermitted(user.getRole())) {
                notifyObservers("Reserva Negada: " + user.getName() + " tentou reservar a sala " + room.getRoomNumber());
                throw new Exception("Apenas Professores podem reservar a sala " + room.getRoomNumber());
            }
        }
        
        // verificação para a sala individual 
        if(room instanceof Individual_Proxy) {
            if(!((Individual_Proxy) room).reservePermitted(user.getRole())) {
                notifyObservers("Reserva Negada: " + user.getName() + " tentou reservar a sala " + room.getRoomNumber());
                throw new Exception("Professores não podem reservar salas individuais (Sala " + room.getRoomNumber() + ")");
            }
        }
        
        this.user = user;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.start_schedule = LocalDateTime.parse(start_schedule, formatter);
        this.end_schedule = LocalDateTime.parse(end_schedule, formatter);
        this.room = room;
        
        notifyObservers("Reserva Solicitada: " + this.user.getName() + " " + this.room.getRoomNumber());
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(this, message);
        }
    }


    public User getUser() {
        return user;
    }

    public LocalDateTime getStart_schedule() {
        return start_schedule;
    }

    public LocalDateTime getEnd_schedule() {
        return end_schedule;
    }

    public Room getRoom() {
        return room;
    }
}
