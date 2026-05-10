import Rooms.*;
public class App {
    public static void main(String[] args) throws Exception {

        // Teste de criação de reserva
        Reservation reservation = new Reservation();
        User user1 = new User("Marcos", "Aluno");
        User user2 = new User("Ana", "Professor");
        Room room1 = FactoryRoom.createRoom("individual", 101, 0);

        reservation.setStrategy(new Priority_Reservation());

        Reserve reserve1 = new Reserve(user1, "2024-06-01 14:00", "2024-06-01 16:00", room1); 
        reservation.addReserve(reserve1);

        Reserve reserve2 = new Reserve(user2, "2024-06-01 15:00", "2024-06-02 17:00", room1);   
        reservation.addReserve(reserve2);

        reservation.listReserves();
        reservation.removeReserve(reserve1);
        
        reserve2 = new Multimedia_Decorator(reserve2, "Projetor");
    }
}
