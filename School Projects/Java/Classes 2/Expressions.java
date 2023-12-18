
/*
 * Un exemple de solution.
 * Ici toutes les classes sont dans le même fichier pour simplifier la lecture.
 * A modifier bien sûr en cas de distribution d'un package où des classes
 * devraient être publiques.
 *
 * TROIS solutions sont proposées la méthode toStringInfixe():
 *  1. Méthode "naive", mise en oeuvre sur les expressions unaires
 *     la méthode toStringInfixe n'est *PAS* redéfinie au niveau de ExpUnaire
 *     Il y a du code "duplique" dans toutes les filles...
 *  => le but est donc de factoriser la partie "commune" au niveau de ExpUnaire
 *
 *  2. Solution avec attribut, sur les expressions binaires
 *
 *  3. Solution avec méthode abstraite (sur les expressions binaires, commentée)
 * 
 * Pour l'évaluation il faut une méthode (comme la solution 3 précédente).
 */


//=========================================================================
abstract class ExpAbstraite {

    abstract public String toStringInfixe();

    @Override
    public String toString() {
        return "Je suis une expression et voila ma représentation infixée => "
            + this.toStringInfixe();
    }
    // Notez bien qu'on peut tout à fait appeler une méthode abstraite.
    // Dès lors qu'elle est déclarée, elle existera. Et la liaison dynamique
    // (tardive) fera l'appel vers la méthodes du type concret de this.

    /**
     * Evalue cette expression dans l'environnement env et retourne sa valeur
     * double Lève une RunTimeException si une variable apparaissant dans cette
     * expression n'a pas de valeur dans env.
     */
    abstract public double evaluer(Env env);
}


//=========================================================================
class Constante extends ExpAbstraite {
    double val;

    public Constante(double val) {
        this.val = val;
    }

    @Override
    public String toStringInfixe() {
        return Double.toString(val); 
        // pas possible de faire val.toString(), val n'est pas une référence
        // vers un objet!
        // Ici on passe par la classe "wrapper" Double du type de base double
        // Alternatives:
        //   return String.toString(val)   // méthode de classe de String
        //   return  "" + val;             // triche
    }

    @Override
    public double evaluer(Env env) {
        return val;
    }
}


//=========================================================================
class Variable extends ExpAbstraite {
    String nom;

    public Variable(String nom) {
        this.nom = nom;
    }

    @Override
    public String toStringInfixe() {
        return nom;
    }

    @Override
    public double evaluer(Env env) {
        return env.obtenirValeur(nom);
    }
}



//=========================================================================
// Solution 1: naive (sur les unaires)
//=========================================================================
//  Ici toStringInfixe() n'est *PAS* redéfinie au niveau de ExpUnaire
//	=> tout dans les filles, avec de la "redondance": seule la chaîne de 
//  l'opérateur change, ou le calcul pour l'évaluation
//=========================================================================
abstract class ExpUnaire extends ExpAbstraite {
    protected ExpAbstraite operande;	// protected, pour les filles

    public ExpUnaire(ExpAbstraite e) {
        operande = e;
    }
}


class UnaireCos extends ExpUnaire {
    public UnaireCos(ExpAbstraite e) {
        super(e);
    }

    @Override
    public String toStringInfixe() {
        return "cos(" + operande.toStringInfixe() + ")";	// redondance
    }

    @Override
    public double evaluer(Env env) {
    	return Math.cos(operande.evaluer(env));             // redondance
    }
}


class UnaireSin extends ExpUnaire {
    public UnaireSin(ExpAbstraite e) {
        super(e);
    }

    public String toStringInfixe() {
        return "sin(" + operande.toStringInfixe() + ")";	// redondance
    }


    @Override
    public double evaluer(Env env) {
    	return Math.sin(operande.evaluer(env));             // redondance
    }
}



//=========================================================================
// Solution 2, avec attribut (affichage sur les binaires)
//=========================================================================
// Ici l'affichage utilise une String représentant l'opérateur,
// initialisée par les constructeurs des filles.
// toStringInfixe() peut donc être écrite une seule fois dans ExpBinaire
//=========================================================================

abstract class ExpBinaire extends ExpAbstraite {
    // Opérandes
    private ExpAbstraite opGauche;	// private, plus besoin de protected!
    private ExpAbstraite opDroite;

    // Chaîne représentant l'opérateur de cette exp binaire
    private String imageOperateur;

    public ExpBinaire(ExpAbstraite g, ExpAbstraite d, String imageOperateur) {
        opGauche = g;
        opDroite = d;
        this.imageOperateur = imageOperateur;
    }

    // on écrit UNE SEULE FOIS le schéma du parcours infixé:
    //  traiter le sous-arbre gauche, traiter le noeud, traiter le sous-arbre droit
    @Override
    public String toStringInfixe() {
        return "(" + opGauche.toStringInfixe() +
            " " + imageOperateur + " "			// on utilise l'image
            + opDroite.toStringInfixe() + ")";
    }


    // Evaluation:
    //  - on introduit une nouvelle méthode qui fait réellement le calcul
    //  - on écrit le corps de evaluer(Env) ici, UNE SEULE FOIS

    abstract double executeOperation(double evalG, double evalD);

    @Override
    public double evaluer(Env env) {
    	// on évalue les deux opérandes
    	double evalG = opGauche.evaluer(env);
    	double evalD = opDroite.evaluer(env);
        
    	// on invoque la méthode abstraite ci-dessus
    	return executeOperation(evalG, evalD);
    }
}


//=========================================================================
class BinaireMult extends ExpBinaire {

    public BinaireMult(ExpAbstraite g, ExpAbstraite d) {
        super(g, d, "*");	// init avec la chaîne adéquate
        // Notez que sans le mécanisme de "string pool" des chaînes statiques
        // on enverrait un nouvel objet "*" pour chaque mult; pas top.
    }

    @Override
    double executeOperation(double evalG, double evalD) {
    	return evalG * evalD;
    }
}

//=========================================================================
class BinairePlus extends ExpBinaire {

    private static String op = new String("+");

    public BinairePlus(ExpAbstraite g, ExpAbstraite d) {
        super(g, d, op);	
        // ici c'est plus générique: op est bien une instance partagée entre
        // toutes les instances de BinairePlus, jamais de copie.
    }

    @Override
    double executeOperation(double evalG, double evalD) {
    	return evalG + evalD;
    }
}




//=========================================================================
// Solution 3: avec méthode abstraite
//=========================================================================
// Ici l'affichage utilise une méthode abstraite qui retourne une chaîne
// représentant l'opérateur.
// Cette méthode doit être redéfinie par les filles.
// toStringInfixe() peut donc être écrite une seule fois dans ExpBinaire
//=========================================================================

/*
abstract class ExpBinaire extends ExpAbstraite {
    // opérandes
    private ExpAbstraite opGauche;	// private, plus besoin de protected!
    private ExpAbstraite opDroite;

    public ExpBinaire(ExpAbstraite g, ExpAbstraite d) {
        opGauche = g;
        opDroite = d;
    }

    // On introduit une nouvelle méthode retournant une image de l'opérateur
    abstract protected String getImageOperateur();

    @Override
    public String toStringInfixe() {
        return "(" + opGauche.toStringInfixe()
                + " " + getImageOperateur() + " "	// on utilise la méthode
                + opDroite.toStringInfixe() + ")";
    }
}


//=========================================================================
class BinaireMult extends ExpBinaire {

    public BinaireMult(ExpAbstraite g, ExpAbstraite d) {
        super(g, d);
    }

    @Override
    protected String getImageOperateur() {
        return "*";
    }
}

//=========================================================================
class BinairePlus extends ExpBinaire {

    public BinairePlus(ExpAbstraite g, ExpAbstraite d) {
        super(g, d);
    }

    @Override
    protected String getImageOperateur() {
        return "*";
    }
}
*/	// Fin de la solution 3
