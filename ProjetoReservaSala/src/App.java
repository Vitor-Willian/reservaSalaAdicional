import Rooms.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws Exception {

        List<Room> rooms = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Reservation reservation = Reservation.getInstance();

        boolean running = true;

        while (running) {
            System.out.println("Bem-vindo ao sistema de reserva de salas!");
            System.out.println("1. Criar usuário");
            System.out.println("2. Criar sala");
            System.out.println("3. Fazer reserva");
            System.out.println("4. Listar reservas");
            System.out.println("5. Definir estratégia de reserva");
            System.out.println("6. Modificar reserva");
            System.out.println("7. Sair\n");

            int choice = Input.getInt("Escolha uma opção: ");

            switch (choice) {
                case 1:
                    String name = Input.getString("Digite o nome do usuário: ");
                    String role = Input.getString("Digite o papel do usuário (Professor/Aluno): ");
                   
                    User user = new User(name, role);

                    if (!user.hasError()) {
                        users.add(user);
                        System.out.println("Usuário criado com sucesso!\n");
                    }
                    break;

                case 2:
                    int roomNumber = Input.getInt("Digite o número da sala: ");
                    String roomType = Input.getString("Digite o tipo da sala (Individual/Grupo/Lab): ");
                    int capacity = Input.getInt("Digite a capacidade da sala (salas individuais tem capacidade 1): ");

                    Room room = FactoryRoom.createRoom(roomType, roomNumber, capacity);
                    
                    if (room != null) {
                        rooms.add(room);
                        System.out.println("Sala criada com sucesso!\n");
                    }
                    break;

                case 3:
                    if (users.isEmpty() || rooms.isEmpty()) {
                        System.out.println("É necessário ter pelo menos um usuário e uma sala para fazer uma reserva.\n");
                        break;
                    }

                    String userName = Input.getString("Digite o nome do usuário que fará a reserva: ");
                    if (users.stream().noneMatch(u -> u.getName().equals(userName))) {
                        System.out.println("Usuário não encontrado: " + userName + "\n");
                        break;
                    }

                    String roomNumStr = Input.getString("Digite o número da sala a ser reservada: ");
                    if (rooms.stream().noneMatch(r -> Integer.toString(r.getRoomNumber()).equals(roomNumStr))) {
                        System.out.println("Sala não encontrada: " + roomNumStr + "\n");
                        break;
                    }

                    String startSchedule = Input.getString("Digite o horário de início da reserva (formato: yyyy-MM-dd HH:mm): ");
                    String endSchedule = Input.getString("Digite o horário de término da reserva (formato: yyyy-MM-dd HH:mm): ");

                    Reserve reserve = new Reserve(users.stream().filter(u -> u.getName().equals(userName)).findFirst().orElse(null),
                                              startSchedule, endSchedule,
                                              rooms.stream().filter(r -> Integer.toString(r.getRoomNumber()).equals(roomNumStr)).findFirst().orElse(null));
                    
                    System.out.println("Tudo certo até aqui, deseja adicionar um serviço extra?");
                    System.out.println("1. Serviço de limpeza");
                    System.out.println("2. Equipamentos multimídia");
                    System.out.println("3. Nenhum");

                    int extraChoice = Input.getInt("Escolha uma opção: ");
                    switch (extraChoice) {
                        case 1:
                            reserve = new Cleaning_Decorator(reserve);
                            break;

                        case 2:
                            String equipment = Input.getString("Digite o equipamento multimídia desejado: ");
                            reserve = new Multimedia_Decorator(reserve, equipment);
                            break;

                        case 3:
                            break;
                        default:
                            System.out.println("Opção inválida, continuando sem serviço extra.\n");
                    }

                    reservation.addReserve(reserve);
                    System.out.println("Reserva feita com sucesso!\n");
                    break;
                
                case 4:
                    reservation.listReserves();
                    break;
                
                case 5:
                    System.out.println("Escolha a estratégia de reserva:");
                    System.out.println("1. Estratégia ordem de chegada");
                    System.out.println("2. Estratégia de prioridade para professores");
                    int strategyChoice = Input.getInt("Escolha uma opção: ");
                    switch (strategyChoice) {
                        case 1:
                            reservation.setStrategy(new First_Reservation(reservation.getReserves()));
                            break;

                        case 2:
                            reservation.setStrategy(new Priority_Reservation(reservation.getReserves()));
                            break;

                        default:
                            System.out.println("Opção inválida, mantendo a estratégia atual.\n");
                    }
                    break;
                
                case 6:
                    String oldUserName = Input.getString("Digite o nome do usuário da reserva a ser modificada: ");
                    String oldRoomNumStr = Input.getString("Digite o número da sala da reserva a ser modificada: ");
                    String oldStartSchedule = Input.getString("Digite o horário de início da reserva a ser modificada (formato: yyyy-MM-dd HH:mm): ");
                    String oldEndSchedule = Input.getString("Digite o horário de término da reserva a ser modificada (formato: yyyy-MM-dd HH:mm): ");

                    Reserve oldReserve = reservation.getReserves().stream()
                        .filter(r -> r.getUser().getName().equals(oldUserName) &&
                                     Integer.toString(r.getRoom().getRoomNumber()).equals(oldRoomNumStr) &&
                                     r.getStart_schedule().equals(LocalDateTime.parse(oldStartSchedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))) &&
                                     r.getEnd_schedule().equals(LocalDateTime.parse(oldEndSchedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
                        .findFirst()
                        .orElse(null);
                    if (oldReserve == null) {
                        System.out.println("Reserva não encontrada para modificação.\n");
                    }
                    String newUserName = Input.getString("Digite o nome do usuário para a nova reserva: ");
                    String newRoomNumStr = Input.getString("Digite o número da sala para a nova reserva: ");
                    String newStartSchedule = Input.getString("Digite o horário de início para a nova reserva (formato: yyyy-MM-dd HH:mm): ");
                    String newEndSchedule = Input.getString("Digite o horário de término para a nova reserva (formato: yyyy-MM-dd HH:mm): ");

                    Reserve newReserve = new Reserve(users.stream().filter(u -> u.getName().equals(newUserName)).findFirst().orElse(null),
                                              newStartSchedule, newEndSchedule,
                                              rooms.stream().filter(r -> Integer.toString(r.getRoomNumber()).equals(newRoomNumStr)).findFirst().orElse(null));
                    reservation.updateReserve(oldReserve, newReserve);
                    break;

                case 7:
                    running = false;
                    System.out.println("Saindo do sistema. Até mais!");
                    break;
            }       
        }
    }
}
