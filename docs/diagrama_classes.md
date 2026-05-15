# Diagrama de Classes Completo do Sistema (Atualizado)

Abaixo está a representação UML completa e integrada de todo o sistema de Reserva de Salas. Este diagrama une a arquitetura original (Observadores, Estratégias, Decoradores) com a nova implementação do padrão Proxy de Proteção e Factory Method para o controle de acesso às salas.

```mermaid
classDiagram
    %% --- Padrão Observer Base ---
    class Subject {
        <<interface>>
        +addObserver(observer: Observer) void
        +removeObserver(observer: Observer) void
        +notifyObservers(message: String) void
    }
    class Observer {
        <<interface>>
        +update(subject: Subject, message: String) void
    }

    %% --- Usuário e Modelo de Reserva Central ---
    class User {
        -name: String
        -role: String
        -error: boolean
        +getName() String
        +getRole() String
        +hasError() boolean
        +update(subject: Subject, message: String) void
    }
    Observer <|.. User : implements

    class Reserve {
        -user: User
        -start_schedule: LocalDateTime
        -end_schedule: LocalDateTime
        -room: Room
        -observers: List~Observer~
        +addObserver(observer: Observer) void
        +removeObserver(observer: Observer) void
        +notifyObservers(message: String) void
        +getUser() User
        +getStart_schedule() LocalDateTime
        +getEnd_schedule() LocalDateTime
        +getRoom() Room
    }
    Subject <|.. Reserve : implements
    Reserve --> User : notificaObservers (implícito na lista de observadores)
    Reserve --> Room : associa-se

    %% --- Gerenciador Singleton e Padrão Strategy ---
    class Reservation {
        -strategy: Reservation_Strategy
        -reserves: List~Reserve~
        -static instance: Reservation
        -Reservation()
        +static getInstance() Reservation
        +setStrategy(strategy: Reservation_Strategy) void
        +addReserve(reserve: Reserve) void
        +removeReserve(reserve: Reserve) void
        +updateReserve(oldR: Reserve, newR: Reserve) void
        +getReserves() List~Reserve~
        +roomAvailability(room: Room, start: String, end: String) boolean
        +report() void
    }

    class Reservation_Strategy {
        <<abstract>>
        #reserves: List~Reserve~
        +Reservation_Strategy(reserves: List~Reserve~)
        #addReserve(reserve: Reserve) void
        +getReserves() List~Reserve~
        +setReserves(reserves: List~Reserve~) void
    }
    Reservation --> Reservation_Strategy : usa
    Reservation_Strategy ..> Reserve : gerencia/conflita (Conceitual)

    class First_Reservation {
        +addReserve(reserve: Reserve) void
    }
    Reservation_Strategy <|-- First_Reservation : extends

    class Priority_Reservation {
        +addReserve(reserve: Reserve) void
    }
    Reservation_Strategy <|-- Priority_Reservation : extends

    %% --- Padrão Decorator para Serviços Extras nas Reservas ---
    class Reserve_Decorator {
        <<abstract>>
        #reserve: Reserve
        +Reserve_Decorator(reserve: Reserve)
    }
    Reserve <|-- Reserve_Decorator : extends/decorates (conforme UML do fundo preto)

    class Multimedia_Decorator {
        -Equipment: String
        +Multimedia_Decorator(reserve: Reserve, equipment: String)
        -addMultimediaEquipment() void
        +getEquipment() String
    }
    Reserve_Decorator <|-- Multimedia_Decorator : extends

    class Cleaning_Decorator {
        +Cleaning_Decorator(reserve: Reserve)
        -addCleaningService() void
    }
    Reserve_Decorator <|-- Cleaning_Decorator : extends

    %% --- NOVA ESTRUTURA: Padrão Room e Proxy (Controle de Acesso) ---
    class Room {
        <<interface>>
        +getRoomNumber() int
        +getRoomType() String
    }

    class Room_Proxy {
        <<interface>>
        +reservePermitted(role: String) boolean
    }
    Room <|.. Room_Proxy : extends interface

    %% Salas Reais (Real Subjects)
    class Individual_Room {
        -roomType: String
        -roomNumber: int
        +getRoomType() String
        +getRoomNumber() int
    }
    Room <|.. Individual_Room : implements

    class Lab_Room {
        -roomType: String
        -roomNumber: int
        +getRoomType() String
        +getRoomNumber() int
    }
    Room <|.. Lab_Room : implements

    class Group_Room {
        -roomType: String
        -roomNumber: int
        -capacity: int
        +getRoomType() String
        +getRoomNumber() int
        +getCapacity() int
    }
    Room <|.. Group_Room : implements

    %% Proxies de Proteção
    class Individual_Proxy {
        -room: Individual_Room
        +reservePermitted(role: String) boolean
        +getRoomNumber() int
        +getRoomType() String
    }
    Room_Proxy <|.. Individual_Proxy : implements
    Individual_Proxy --> Individual_Room : controla acesso/protege

    class Lab_Proxy {
        -room: Lab_Room
        +reservePermitted(role: String) boolean
        +getRoomNumber() int
        +getRoomType() String
    }
    Room_Proxy <|.. Lab_Proxy : implements
    Lab_Proxy --> Lab_Room : controla acesso/protege

    %% Fábrica
    class FactoryRoom {
        +static createRoom(type: String, roomNumber: int, capacity: int) Room
    }
    FactoryRoom ..> Room : cria instâncias concretas (Salas ou Proxies)

    %% Conexões Finais de Integridade
    Reservation o-- Reserve : agrega lista de Reservas