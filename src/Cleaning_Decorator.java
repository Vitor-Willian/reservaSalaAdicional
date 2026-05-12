public class Cleaning_Decorator extends Reserve_Decorator {

    public Cleaning_Decorator(Reserve reserve) {
        super(reserve);
        addCleaningService();
    }

    private void addCleaningService() {
        notifyObservers("Serviço de Limpeza requisitado: " + getUser().getName() + " " + getRoom().getRoomNumber());
    }
}
