import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Rooms.FactoryRoom;
import Rooms.Room;

public class Commands {
    void createUser(List<User> users) {
        String name = Input.getString("Digite o nome do usuário: ");
        String role = Input.getString("Digite o papel do usuário (Professor/Aluno): ");
        
        User user = new User(name, role);

        if (!user.hasError()) {
            users.add(user);
            System.out.println("Usuário criado com sucesso!\n");
        }
    }

    void createRoom(List<Room> rooms) {
        int roomNumber = Input.getInt("Digite o número da sala: ");
        String roomType = Input.getString("Digite o tipo da sala (Individual/Grupo/Lab): ");
        int capacity = Input.getInt("Digite a capacidade da sala (salas individuais tem capacidade 1): ");

        Room room = FactoryRoom.createRoom(roomType, roomNumber, capacity);
        
        if (room != null) {
            rooms.add(room);
            System.out.println("Sala criada com sucesso!\n");
        }
    }

    void createReserve(List<User> users, List<Room> rooms, Reservation reservation) {
        if (users.isEmpty() || rooms.isEmpty()) {
            System.out.println("É necessário ter pelo menos um usuário e uma sala para fazer uma reserva.\n");
            return;
        }

        String userName = Input.getString("Digite o nome do usuário que fará a reserva: ");
        if (users.stream().noneMatch(u -> u.getName().equals(userName))) {
            System.out.println("Usuário não encontrado: " + userName + "\n");
            return;
        }

        String roomNumStr = Input.getString("Digite o número da sala a ser reservada: ");
        if (rooms.stream().noneMatch(r -> Integer.toString(r.getRoomNumber()).equals(roomNumStr))) {
            System.out.println("Sala não encontrada: " + roomNumStr + "\n");
            return;
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
    }

    void setStrategy(Reservation reservation){
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
    }

    void modifyReserve(List<User> users, List<Room> rooms, Reservation reservation) {
        String userName = Input.getString("Digite o nome do usuário da reserva a ser modificada: ");
        String roomNumStr = Input.getString("Digite o número da sala da reserva a ser modificada: ");
        String startSchedule = Input.getString("Digite o horário de início da reserva a ser modificada (formato: yyyy-MM-dd HH:mm): ");
        String endSchedule = Input.getString("Digite o horário de término da reserva a ser modificada (formato: yyyy-MM-dd HH:mm): ");

        Reserve reserveToModify = reservation.getReserves().stream()
            .filter(r -> r.getUser().getName().equals(userName) &&
                         Integer.toString(r.getRoom().getRoomNumber()).equals(roomNumStr) &&
                         r.getStart_schedule().equals(LocalDateTime.parse(startSchedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))) &&
                         r.getEnd_schedule().equals(LocalDateTime.parse(endSchedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
            .findFirst()
            .orElse(null);

        if (reserveToModify == null) {
            System.out.println("Reserva não encontrada para modificação.\n");
            return;
        }

        reservation.removeReserve(reserveToModify);
        System.out.println("Reserva encontrada. Agora, insira os novos detalhes para a reserva:\n");
        createReserve(users, rooms, reservation);
    }

    void removeReserve(Reservation reservation) {
        String removeUserName = Input.getString("Digite o nome do usuário da reserva a ser removida: ");
        String removeRoomNumStr = Input.getString("Digite o número da sala da reserva a ser removida: ");
        String removeStartSchedule = Input.getString("Digite o horário de início da reserva a ser removida (formato: yyyy-MM-dd HH:mm): ");
        String removeEndSchedule = Input.getString("Digite o horário de término da reserva a ser removida (formato: yyyy-MM-dd HH:mm): ");

        Reserve reserveToRemove = reservation.getReserves().stream()
            .filter(r -> r.getUser().getName().equals(removeUserName) &&
                            Integer.toString(r.getRoom().getRoomNumber()).equals(removeRoomNumStr) &&
                            r.getStart_schedule().equals(LocalDateTime.parse(removeStartSchedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))) &&
                            r.getEnd_schedule().equals(LocalDateTime.parse(removeEndSchedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))))
            .findFirst()
            .orElse(null);
        if (reserveToRemove == null) {
            System.out.println("Reserva não encontrada para remoção.\n");
        } else {
            reservation.removeReserve(reserveToRemove);
            System.out.println("Reserva removida com sucesso!\n");
        }
    }

    void listAvailableRooms(List<Room> rooms, Reservation reservation) {
        String startSchedule = Input.getString("Digite o horário de início para verificar disponibilidade (formato: yyyy-MM-dd HH:mm): ");
        String endSchedule = Input.getString("Digite o horário de término para verificar disponibilidade (formato: yyyy-MM-dd HH:mm): ");

        System.out.println("Salas disponíveis entre " + startSchedule + " e " + endSchedule + ":");
        rooms.stream()
            .filter(r -> reservation.roomAvailability(r, startSchedule, endSchedule))
            .forEach(r -> System.out.println("Sala " + r.getRoomNumber() + " - Tipo: " + r.getRoomType() + "\n"));
    }
}