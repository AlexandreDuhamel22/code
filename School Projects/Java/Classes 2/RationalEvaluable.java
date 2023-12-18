
//=========================================================================
// un RationalEvaluable ETEND Rational en REALISANT l'interface

class RationalEvaluable extends Rational implements Evaluable {

    // definition des constructeurs (nécessaire)
    public RationalEvaluable(int num, int denom) {
        super(num, denom);
    }

    public RationalEvaluable(int n) {
        super(n);
    }

    public RationalEvaluable(RationalEvaluable other) {
        super(other);
    }

    // Implémentation de l'interface
    @Override
    public double evaluer() {
        return toDouble();
    }
}

