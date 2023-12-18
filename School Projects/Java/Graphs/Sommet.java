import java.util.*;


/**
 * Un Sommet est defini par son label et ses Sommet successeur.
 * Discussion pour les successeurs: Set vs List ?
 * Set: un seul arc possible entre deux sommets
 * List: plusieurs arcs possibles entre deux sommets (multigraphe)
 *  => ici on prend un Set
 *
 */

class Sommet {

    private String label;
    private Collection<Sommet> succ;

    public Sommet(String nom) {
        label = nom;
        succ = new HashSet<Sommet>(); 		// pas de doublons
        // succ = new LinkedList<Sommet>(); 	// multigraphe possible
        // il suffit de change de type dynamique pour s'adapter a la specif!
    }

    //O(1)
    public boolean ajouteSuccesseur(Sommet suiv) {
        return succ.add(suiv);
    }


    public Iterator<Sommet> iteratorSuccesseurs() {
        return succ.iterator();
    }

    @Override
    public String toString() {
        return label;
    }

    // Pour equals et hashCode, delegation à l'attribut label

    /* Deux sommets sont "egaux" si leur labels le sont. */
    @Override
    public boolean equals(Object o) {
        return o instanceof Sommet && this.label.equals(((Sommet) o).label);
    }

    /* On a bien coherence entre equals() et hashcode():
       Si a.equals(b) alors a.hashCode() == b.hashCode() */
    @Override
    public int hashCode() {
        return label.hashCode();
    }
}
