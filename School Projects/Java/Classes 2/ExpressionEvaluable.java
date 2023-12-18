//=========================================================================
// Une ExpressionEvaluable encapsule une expression + un contexte

class ExpressionEvaluable implements Evaluable {
    private ExpAbstraite expr;
    private Env env;

    public ExpressionEvaluable(ExpAbstraite expr, Env env) {
        this.expr = expr;
        this.env = env;
    }

    @Override
    public double evaluer() {
        return expr.evaluer(env);
    }

    @Override
    public String toString() {
        return expr.toStringInfixe();
    }
}
