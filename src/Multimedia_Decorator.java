public class Multimedia_Decorator extends Reserve_Decorator {
    private String Equipment;
    public Multimedia_Decorator(Reserve reserve, String equipment) {
        super(reserve);
        this.Equipment = equipment;
        addMultimediaEquipment();
    }

    private void addMultimediaEquipment() {
        notifyObservers("Equipamento Multimídia Adicionado: "
         + getUser().getName() + " " + getRoom().getRoomNumber() 
         + " - " + this.Equipment );
    }

    public String getEquipment() {
        return Equipment;
    }
    
}
