# Sistema de Reserva de Salas

## Visão Geral

Sistema de reserva de salas desenvolvido em Java utilizando padrões de design orientados a objetos. O projeto permite gerenciar usuários, salas e reservas com suporte a múltiplas estratégias de reserva, notificações em tempo real e relatórios de ocupação.

### Funcionalidades Principais

- **Gestão de Usuários**: Criar e gerenciar usuários do sistema
- **Gestão de Salas**: Criar diferentes tipos de salas (Individual, Grupo, Laboratório)
- **Reservas de Salas**: Fazer, modificar e cancelar reservas
- **Controle de Acesso**: Restrição de reservas baseada no perfil do usuário (alunos não podem reservar laboratórios e professores não podem reservar salas individuais).
- **Estratégias de Reserva**: Implementar diferentes critérios de alocação (Primeira Disponível, Prioritária)
- **Decoradores de Reservas**: Adicionar funcionalidades extras às reservas (Limpeza, Multimedia)
- **Relatórios**: Visualizar todas as reservas realizadas
- **Notificações**: Sistema de observadores para notificar mudanças em reservas

## Como Usar

### Pré-requisitos

- Java 11 ou superior
- VS Code com extensão Language Support for Java (Red Hat)

### Compilação

```bash
javac -d bin src/**/*.java src/Rooms/*.java
```

### Execução

```bash
java -cp bin App
```

### Menu Principal

A aplicação oferece um menu interativo com as seguintes opções:

1. **Criar Usuário** - Registrar novo usuário no sistema
2. **Criar Sala** - Criar nova sala (Individual, Grupo ou Laboratório)
3. **Fazer Reserva** - Reservar uma sala em um período específico
4. **Listar Salas Disponíveis** - Visualizar salas disponíveis em um período
5. **Definir Estratégia de Reserva** - Alternar entre estratégias de alocação
6. **Modificar Reserva** - Atualizar uma reserva existente
7. **Relatório de Reservas** - Listar todas as reservas realizadas
8. **Remover Reserva** - Cancelar uma reserva
9. **Sair** - Encerrar a aplicação

### Fluxo Básico

1. Inicie a aplicação
2. Crie pelo menos um usuário
3. Crie pelo menos uma sala
4. Faça uma reserva selecionando um usuário, sala, data e hora
5. Consulte relatórios para acompanhar as reservas

## Estrutura do Projeto

### Pacotes

- **src/** - Classes principais do sistema
  - `App.java` - Ponto de entrada da aplicação
  - `Reservation.java` - Gerenciador centralizado de reservas (Singleton)
  - `Reserve.java` - Modelo de reserva
  - `User.java` - Modelo de usuário
  - `Commands.java` - Controlador de comandos
  - Estratégias de reserva e decoradores

- **src/Rooms/** - Classes relacionadas a salas
  - `Room.java` - Interface base de sala
  - `Room_Proxy.java` - Interface para o controle de acesso das salas
  - `Individual_Room.java` - Sala individual
   - `Individual_Proxy.java` - Proxy de controle de acesso para sala individual
  - `Group_Room.java` - Sala em grupo
  - `Lab_Room.java` - Laboratório
  - `Lab_Proxy.java` - Proxy de controle de acesso para laboratório
  - `FactoryRoom.java` - Factory para criação de salas

## Padrões de Design Utilizados

- **Singleton**: Classe `Reservation` para garantir única instância
- **Strategy**: Diferentes estratégias de reserva
- **Decorator**: Adição de funcionalidades às reservas
- **Observer**: Notificação de mudanças em reservas
- **Factory**: Criação de diferentes tipos de salas
- **Proxy**: Controle de acesso para garantir as regras de permissão em salas individuais e laboratórios de acordo com o cargo do usuário.
## Autores

Marcos Ueda, Vitor Willian Peneluppi Pinto e Natália Amaral.

## Licença

Este projeto é fornecido para fins educacionais.
