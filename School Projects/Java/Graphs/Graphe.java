public interface Graphe {
    /**
     * Ajoute un nouveau sommet d'étiquette label.
     * Si un sommet de même étiquette existe déjà, cette méthode ne fait rien.
     */
    public void ajouteSommet(String label);

    /**
     * Ajoute un nouvel arc entre deux sommets.
     * Si les sommets n'existent pas encore, ils sont ajoutés au graphe.
     * Si un arc existe déjà entre ces deux sommets, on ne fait rien
     * (sauf en cas de multi-graphe).
     */
    public void ajouteArc(String labelDepart, String labelArrivee);

    /**
     * Retourne une chaîne contenant la liste des sommets, puis celle des arcs.
     */
    @Override
    public String toString();

    /**
     * Cherche l'existence d'un chemin entre deux sommets du graphe.
     * 
     * @return true si un chemin existe
     */
    public boolean existeChemin(String labelDepart, String labelArrivee);
}
