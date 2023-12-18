import java.util.*;

class Env {

    // Cette fois, on associe des objets Evaluable
    private Map<String, Evaluable> table;

    public Env() {
        this.table = new HashMap<String, Evaluable>();
    }

    public void associer(String s, Evaluable val) {
        table.put(s, val);
    }

    public void associer(String s, double val) {
        table.put(s, new Evaluable() {
                public double evaluer() {
                    return val;
                }
            });
    }

    public double obtenirValeur(String s) {
        if (contient(s)) {
            return table.get(s).evaluer();
        } else {
            throw new RuntimeException("variable " + s
                    + " inconnue dans l'environnement");
        }
    }

    public boolean contient(String s) {
        return table.containsKey(s);
    }

    @Override
    public String toString() {
        return "Environnement : " + this.table;
    }
}
