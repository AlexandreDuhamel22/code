
public class TestGraphe {

    public static void main(String[] args) {
        System.out.println("Graphe SUCC");
        testGraphe(new GrapheSuccesseurs());

        System.out.println();
        System.out.println("Graphe MAP");
        testGraphe(new GrapheMap());
    }


    private static void testGraphe(Graphe g) {

        for (int i = 0; i < 10; i++) {
            g.ajouteSommet(String.valueOf(i));
        }

        g.ajouteArc("0", "1");
        g.ajouteArc("0", "2");
        g.ajouteArc("1", "8");
        g.ajouteArc("2", "3");
        g.ajouteArc("2", "4");
        g.ajouteArc("3", "6");
        g.ajouteArc("3", "0");
        g.ajouteArc("4", "1");
        g.ajouteArc("4", "5");
        g.ajouteArc("5", "8");
        g.ajouteArc("5", "9");
        g.ajouteArc("6", "4");
        g.ajouteArc("6", "2");
        g.ajouteArc("6", "5");
        g.ajouteArc("9", "9");
        // test multi-graphe
        g.ajouteArc("0", "1");
        g.ajouteArc("0", "2");
        g.ajouteArc("9", "9");

        System.out.println(g);

        System.out.println("chemin 0 -> 9 (attendu: true)  : "
                + g.existeChemin("0", "9"));
        System.out.println("chemin 5 -> 3 (attendu: false) : "
                + g.existeChemin("5", "3"));
        System.out.println("chemin 6 -> 7 (attendu: false) : "
                + g.existeChemin("6", "7"));
    }

}
