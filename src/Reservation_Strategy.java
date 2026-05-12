import java.util.List;

abstract class Reservation_Strategy {
    protected List<Reserve> reserves;

    public Reservation_Strategy(List<Reserve> reserves) {
        this.reserves = reserves;
    }

    abstract void addReserve(Reserve reserve);
    
    public List<Reserve> getReserves() {
        return this.reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }
}
