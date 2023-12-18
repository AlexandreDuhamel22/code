package AutomateCellulaire.Test;

import AutomateCellulaire.SchellingModel;
import AutomateCellulaire.Simulator.SchellingModelSimulator;
import gui.GUISimulator;

import java.awt.*;
import java.util.*;

/**
 * Classe de test pour le simulateur du modèle de Schelling
 */
public class TestSchellingModelSimulator {
    public static void main(String[] args) {
        // Initialisation de la carte de cellules avec des états aléatoires
        Map<Point, Integer> mapCellInit = new HashMap<>();
        ArrayList<Integer> randomState = new ArrayList<>();

        // Paramètres de simulation
        int nbCouleur = 5;
        int seuilK = 5;

        // Ajout des habitations vides
        int nbHabVide = (int) (2500 * 0.3); // 1/3 des habitations sont vides.
        for (int i = 0; i < nbHabVide; i++) {
            randomState.add(0);
        }

        // Ajout d'habitations de couleur aléatoires entre 1 et nbCouleur
        Random random = new Random();
        for (int i = 0; i < 2500 - nbHabVide; i++) {
            int entierAleatoire = random.nextInt(nbCouleur) + 1; // Génère un entier aléatoire entre 1 et nbCouleur
            randomState.add(entierAleatoire);
        }

        // Mélanger la liste de manière aléatoire
        Collections.shuffle(randomState);

        // Remplissage de la carte avec les états aléatoires
        for (int i = 0; i < 2500; i++) {
            mapCellInit.put(new Point(i / 50, i % 50), randomState.get(i));
        }

        // Création de l'interface graphique
        GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);

        // Création du modèle Schelling avec la carte initiale et le seuil K
        SchellingModel jeu = new SchellingModel(gui, mapCellInit, seuilK);

        // Configuration de l'interface graphique avec le simulateur du modèle Schelling
        gui.setSimulable(new SchellingModelSimulator(jeu, gui));
    }
}