import Rooms.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Reservation reservation = Reservation.getInstance();
        Commands commands = new Commands();

        boolean running = true;

        while (running) {
            System.out.println("Bem-vindo ao sistema de reserva de salas!");
            System.out.println("1. Criar usuário");
            System.out.println("2. Criar sala");
            System.out.println("3. Fazer reserva");
            System.out.println("4. Listar salas disponíveis");
            System.out.println("5. Definir estratégia de reserva");
            System.out.println("6. Modificar reserva");
            System.out.println("7. Relatório de reservas");
            System.out.println("8. Remover reserva");
            System.out.println("9. Sair\n");

            int choice = Input.getInt("Escolha uma opção: ");

            switch (choice) {
                case 1:
                    commands.createUser(users);
                    break;

                case 2:
                    commands.createRoom(rooms);
                    break;

                case 3:
                    commands.createReserve(users, rooms, reservation);
                    break;
                
                case 4:
                    commands.listAvailableRooms(rooms, reservation);
                    break;
                
                case 5:
                    commands.setStrategy(reservation);
                    break;
                
                case 6:
                    commands.modifyReserve(users, rooms, reservation);
                    break;
                
                case 7:
                    reservation.report();
                    break;

                case 8:
                    commands.removeReserve(reservation);
                    break;

                case 9:
                    running = false;
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
            }       
        }
    }
}
