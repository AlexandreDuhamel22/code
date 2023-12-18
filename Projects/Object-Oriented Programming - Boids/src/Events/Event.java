package Events;

/**
 * Classe représentant un evenement devait arriver a une date donnée
 */
public abstract class Event {
    private long date;

    public Event(long date){
        this.date=date;
    };

    public long getDate(){
        return this.date;
    }

    protected abstract void execute();

}
