package SimuBalls;

import Events.Event;
import Events.EventManager;

/**
 * Classe représentant un la translation des balls comme un Event
 */
public class BallsEvent extends Event {
    public Balls balls;
    public EventManager eventManager;
    public BallsEvent(long date, Balls balls, EventManager manager){
        super(date);
        this.balls=balls;
        this.eventManager=manager;
    }

    public void execute(){
        this.balls.translate();
        this.eventManager.addEvent(new BallsEvent(getDate()+1,this.balls,this.eventManager));
    }


}
