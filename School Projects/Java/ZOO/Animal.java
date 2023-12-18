
abstract class Animal {
    private String nom;
    protected int poids;
    protected Regime regime;

    protected Animal(String nom, int poids, Regime regime) {
        this.nom = nom;
        this.poids = poids;
        this.regime = regime;
    }

    public void crier() {
        System.out.print(toString() + " crie...");
    }

    @Override
    public String toString() {
        return nom;
    }

    // On ne sait ecrire cette methode ici...
    // mais elle doit être déclarée pour pouvoir être utilisee
    // dans la classe Zoo => abstraite
    abstract public int coutNourriture();
}



//*************************************************************
class Canard extends Animal {
    private String couleurPlumes;

    // gestion du regime v1:
    // ici chaque Canard crée une instance de régime. C'est en fait
    // utile si on voulait des régimes différents pour les canards.
    // Mais s'ils mangent tous la même chose, il y a des instances
    // dupliquées de Regime, pas une instance partagée.

    public Canard(String nom, int poids, String couleurPlumes) {
        super(nom, poids, new Regime("graines", 10));
        this.couleurPlumes = couleurPlumes;
    }

    public String getCouleurPlume() {
        return this.couleurPlumes;
    }

    // Ici on ne modifie pas toString, et le message est change dans crier
    // (avec reutilisation de la methode mere lors de la redefinition)
    @Override
    public void crier() {
        super.crier();
        System.out.println("Ce canard de " + poids + " kilos aux belles plumes "
            + couleurPlumes + " cancane!");
    }

    @Override
    public int coutNourriture() {
        return regime.getPrixKilo();
    }
}


// *************************************************************
class Vache extends Animal {
    private int nbTaches;

    // gestion du regime v2:
    // Si toutes les instances de Vache ont le même régime,
    // on peut PARTAGER une unique instance de Regime (attribut static)

    private final static Regime REGIME_COMMUN = new Regime("paille", 5);

    public Vache(String nom, int poids, int nbTaches) {
        super(nom, poids, REGIME_COMMUN);
        this.nbTaches = nbTaches;
    }

    // Ici on modifie toString, et moins de choses a faire dans crier...
    @Override
    public String toString() {
        // ici on reutilise la methode heritee
        return super.toString() + ", la vache "
            + (nbTaches == 0 ? "sans tache qui tache"
                    : "a " + nbTaches + " taches qui tache");
    }

    @Override
    public void crier() {
        super.crier();
        System.out.println(" il meugle");
    }

    @Override
    public int coutNourriture() {
        return regime.getPrixKilo() * nbTaches;
    }
}
