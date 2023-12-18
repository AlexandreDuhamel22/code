package Events;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * Classe représentant un manager d'evenements qui déclanche l'evenement lorsque sa date est atteinte
 */
public class EventManager {
    public long currentDate;
    public Comparator comparator;
    public Queue<Event> queueEvent;

    //List des events Initial (pour le reset)
    public Queue<Event> queueEventInit;

    public EventManager(){
        this.currentDate=0;
        this.comparator = (Comparator) (new eventComparator());
        this.queueEvent = new PriorityQueue<Event>(comparator);
        this.queueEventInit= new PriorityQueue<Event>(comparator);
    }
    public void addEvent(Event e){
        queueEvent.add(e);
        queueEventInit.add(e);
    }

    public void next(){
        //On avance la date courante
        this.currentDate++;

        //Tant qu'il y a des evenements a la date courante on les execute.
        while(this.queueEvent.peek().getDate()<=this.currentDate){
            Event e= this.queueEvent.poll();
            e.execute();
            //si plus d'events on arret.
            if (this.isFinished()){
                return;
            }
        }
    }
    public boolean isFinished(){
        return this.queueEvent.peek() == null;
    }

    public void restart(){
        this.queueEvent = new PriorityQueue<Event>(this.queueEventInit);
    }
}
