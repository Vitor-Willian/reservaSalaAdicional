import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Rooms.*;

public class Reserve {
    private User user;
    private LocalDate start_date, end_date;
    private LocalDateTime start_time, end_time;
    private Room room;

    public Reserve(User user, LocalDate start_date, LocalDate end_date, LocalDateTime start_time, LocalDateTime end_time, Room room) {
        this.user = user;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public Room getRoom() {
        return room;
    }
}
