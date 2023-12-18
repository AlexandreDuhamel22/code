import java.util.*;

// - chaque sommet connait ses successeurs
// - le graphe ne contient que l'ensemble des sommets. Pour les retrouver
//   par leur nom, on les stocke ds un Map
class GrapheSuccesseurs implements Graphe {

    // Ensemble de tous les sommets du graphe
    // Clé sur les etiquettes => garanti l'unicite
    protected Map<String, Sommet> sommets;
    // Quid de l'idée de stocker les sommets ds un Set?
    //  -> ok pour l'unicité
    //  -> par contre ça coince pour retrouver un sommet par son etiquette,
    //     necessaire ds ajoute Arc => il faudrait parcourir, O(n)!


    public GrapheSuccesseurs() {
        sommets = new HashMap<String, Sommet> ();
    }


    // 0(1)
    @Override
    public void ajouteSommet(String label) {
        if (sommets.containsKey(label)) {
            throw new IllegalArgumentException("sommet deja connu : " + label);
        }
        sommets.put(label, new Sommet(label));
    }
    
    // O(1)
    @Override
    public void ajouteArc(String labelDepart, String labelArrivee) {
        if (!sommets.containsKey(labelDepart)) {
            sommets.put(labelDepart, new Sommet(labelDepart));
        }
        Sommet depart = sommets.get(labelDepart);

        if (!sommets.containsKey(labelArrivee)) {
            ajouteSommet(labelArrivee);
        }
        Sommet arrivee = sommets.get(labelArrivee);

        depart.ajouteSuccesseur(arrivee);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[Sommets]");
        for (String label : sommets.keySet()) {
            s.append(" " + label + ",");
        }
        s.deleteCharAt(s.length() - 1);	// vire la derniere ','

        s.append("\n[Arcs] ");
        for (Sommet depart : sommets.values()) {
            Iterator<Sommet> it = depart.iteratorSuccesseurs();
            while( it.hasNext()) {
                Sommet arrivee = it.next();
                s.append("(" + depart + " -> " + arrivee + ") ");
            }
        }
        s.append("\n");
        return s.toString();
    }


    /* Version iterative ici, recursive dans GrapheMap */
    @Override
    public boolean existeChemin(String labelDepart, String labelArrivee) {
        Sommet depart = sommets.get(labelDepart);
        Sommet arrivee = sommets.get(labelArrivee);
        if (depart == null || arrivee == null)
            return false;

        // Collection des sommets à visiter
        // son type est a choisir selon le parcours voulu!
        //  - une pile pour DFS (profondeur),
        //  - une file pour BFS (largeur),
        //  - PriorityQueue pour BestFirst, ...
        Queue<Sommet> aVisiter = new LinkedList<Sommet> (); // largeur ici

        // Une seconde collection gerer le marquage
        // On prend un HashSet: si un sommet est present, c'est qu'il a deja ete vu
        // La propriete d'unicite de Set fait le travail, et les operatations
        // utlisees sont a cout constant - si hachage uniforme.
        //
        // Par rapport a une version avec attribut de marquage dans Sommet,
        // ou un map qui associe la marque a un Sommet:
        // - même complexité théorique, mais pas de cout d'init
        // - pas besoin de modifier Sommet, et pas de cout memoire
        // - pas de risque sur la valeur du flag marque (oublie de demarquer,
        //   modification par effet de bord, ...)
        Set<Sommet> dejaVus = new HashSet<Sommet> ();

        aVisiter.add(depart);
        while (!aVisiter.isEmpty()) {
            Sommet sommetCourant = aVisiter.remove();
            if (sommetCourant.equals(arrivee)) {
                return true;
            }

            dejaVus.add(sommetCourant);    // marquage
            // ajout des successeurs si pas encore visites
            Iterator<Sommet> successeurs = sommetCourant.iteratorSuccesseurs();
            while (successeurs.hasNext()) {
                Sommet succ = successeurs.next();
                if (!dejaVus.contains(succ)) {
                    aVisiter.add(succ);
                }
            }
        }

        // Si tout le monde a ete visite, c'est qu'il n'y a pas de chemin
        return false;
    }
}
