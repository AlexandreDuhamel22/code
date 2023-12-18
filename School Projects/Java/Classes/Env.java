import java.util.*;

class Env {

    // Un environnement est codé à l'aide d'une table associative
    // qui associe des double à des chaînes de caractères.
    // Notez qu'il faut utiliser la classe Double dans le type de données,
    // qui "encapsule" le type de base double
    private Map<String, Double> table;

    public Env() {
        table = new HashMap<String, Double>();
    }
    public void associer(String s, double val) {
        table.put(s, val); // Autoboxing: conversion double -> Double automatique
    }

    public double obtenirValeur(String s) {
        if (contient(s)) {
            return table.get(s); // Auto-unboxing: conversion Double -> double automatique
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
        return "Environnement : " + table;
    }
}
