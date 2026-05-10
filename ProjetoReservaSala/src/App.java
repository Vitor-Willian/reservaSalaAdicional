import Rooms.*;
public class App {
    public static void main(String[] args) throws Exception {

        Reservation reservation = Reservation.getInstance();
        User user1 = new User("Marcos", "Aluno");
        User user2 = new User("Ana", "Professor");
        Room room1 = FactoryRoom.createRoom("individual", 101, 0);
        Room room2 = FactoryRoom.createRoom("grupo", 202, 30);


        Reserve reserve1 = new Reserve(user1, "2026-05-01 14:00", "2026-05-01 16:00", room1); 
        reservation.addReserve(reserve1);

        Reserve reserve2 = new Reserve(user2, "2026-05-01 15:00", "2026-05-01 17:00", room1);   
        reservation.addReserve(reserve2);

        reservation.listReserves();
        //reservation.removeReserve(reserve1);
        
        reserve2 = new Multimedia_Decorator(new Reserve(user2, "2026-05-01 15:00", "2026-05-01 17:00", room2), "Projetor");
        reservation.addReserve(reserve2);

        reservation.setStrategy(new Priority_Reservation(reservation.getReserves()));
        reservation.listReserves();

    }
}
