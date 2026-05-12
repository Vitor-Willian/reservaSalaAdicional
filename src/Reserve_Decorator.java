import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Reserve_Decorator extends Reserve {
    protected Reserve reserve;

    public Reserve_Decorator(Reserve reserve) {
        super(reserve.getUser(), 
              formatDateTime(reserve.getStart_schedule()), 
              formatDateTime(reserve.getEnd_schedule()), 
              reserve.getRoom());
        this.reserve = reserve;
    }

    private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
}
