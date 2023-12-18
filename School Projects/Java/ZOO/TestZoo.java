
w
public class TestZoo {

    public static void main(String[] args) {
        testZooHeritage();
    }

    private static void testZooHeritage() {
        Zoo ensimag = new Zoo("Ensimag avec Heritage");
        ensimag.ajouteAnimal(new Canard("Akram", 10, "rouge"));
        ensimag.ajouteAnimal(new Vache("Marie", 20, 0));
        ensimag.ajouteAnimal(new Vache("Matthieu", 840, 17));
        ensimag.ajouteAnimal(new Canard("Karine", 50, "\"noir tuxien\""));
        ensimag.ajouteAnimal(new Vache("Nicolas", 60, 12));
        ensimag.ajouteAnimal(new Vache("Thang", 70, 88));
        ensimag.ajouteAnimal(new Canard("Djamel", 25, "jaune"));

        System.out.println(ensimag);
        ensimag.crier();
        ensimag.coutNourriture();
    }
}
