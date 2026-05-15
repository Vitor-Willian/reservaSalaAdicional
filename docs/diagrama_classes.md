# Diagrama de Classes 


```mermaid
classDiagram
    class Room {
        <<interface>>
        +getRoomNumber() int
        +getRoomType() String
    }

    class Room_Proxy {
        <<interface>>
        +reservePermitted(role: String) boolean
    }
    Room <|-- Room_Proxy : extends

    class Individual_Room {
        -roomType: String
        -roomNumber: int
        +getRoomNumber() int
        +getRoomType() String
    }
    Room <|-- Individual_Room : implements

    class Lab_Room {
        -roomType: String
        -roomNumber: int
        +getRoomNumber() int
        +getRoomType() String
    }
    Room <|-- Lab_Room : implements

    class Group_Room {
        -roomType: String
        -roomNumber: int
        -capacity: int
        +getRoomNumber() int
        +getRoomType() String
        +getCapacity() int
    }
    Room <|-- Group_Room : implements

    class Individual_Proxy {
        -room: Individual_Room
        +reservePermitted(role: String) boolean
        +getRoomNumber() int
        +getRoomType() String
    }
    Room_Proxy <|-- Individual_Proxy : implements
    Individual_Proxy --> Individual_Room : controla acesso

    class Lab_Proxy {
        -room: Lab_Room
        +reservePermitted(role: String) boolean
        +getRoomNumber() int
        +getRoomType() String
    }
    Room_Proxy <|-- Lab_Proxy : implements
    Lab_Proxy --> Lab_Room : controla acesso

    class FactoryRoom {
        +createRoom(type: String, roomNumber: int, capacity: int) Room
    }
    FactoryRoom ..> Room : creates