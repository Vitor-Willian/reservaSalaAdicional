import Rooms.*;
public class App {
    public static void main(String[] args) throws Exception {

        // Teste de criação de reserva
        Reservation reservation = new Reservation();
        User user1 = new User("Marcos", "Aluno");
        Room room1 = FactoryRoom.createRoom("individual", 101, 0);
        Reserve reserve1 = new Reserve(user1, "2024-06-01 14:00", "2024-06-01 16:00", room1);
        reservation.addReserve(reserve1);
        reservation.listReserves();

    }
}
