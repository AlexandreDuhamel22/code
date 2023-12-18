package Events;

import java.util.Comparator;

/**
 * Une methode pour comparer deux evènements en fonction de leurs dates.
 */
public class eventComparator implements Comparator<Event> {

    /**
     * Un evenement dont la date est la plus petite est prioritaire
     * @param e1 evenement a comparer
     * @param e2 evenement a comparer
     * @return
     */
    public int compare(Event e1, Event e2) {
        if (e1.getDate() < e2.getDate()) {
            return -1;
        } else if (e1.getDate() > e2.getDate()) {
            return 1;
        } else {
            return 0;
        }
    }
}
