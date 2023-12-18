public class TestEval {
	public static void main(String[] args) {
		// création de l'environnement
		Env env = new Env();
		env.associer("y", 2);
		env.associer("x", 1);
		env.associer("a", 9);
		env.associer("b", 3);
		System.out.println(env);

		ExpAbstraite exp ;
		// teste l'expression préfixée *  y  3
		exp = new BinaireMult(new Variable("y"), new Constante(3)) ;
		System.out.print("Valeur de l'expression " + exp.toStringInfixe() + " -- attendu == 6.0: ");
		System.out.println(exp.evaluer(env));
 
		// teste l'expression préfixée * + x x 5
		exp = new BinaireMult(
		                new BinairePlus(
		                                   new Variable("x"),
		                                   new Variable("x")
		                ),
		                new Constante(5)
		);
		System.out.print("Valeur de l'expression " + exp.toStringInfixe() + " -- attendu == 10.0: ");
		System.out.println(exp.evaluer(env));

		env.associer("x", 2);
		System.out.print("Rebelote avec x==2 -- attendu == 20.0: ");
		System.out.println(exp.evaluer(env));

		// teste l'expression préfixée * -3.5 sin + a b
		exp = new BinaireMult(
		            new Constante(-3.5),
		            new UnaireSin(
		                    new BinairePlus(
		                            new Variable("a"),
		                            new Variable("b")
		                    )
		            )
		);
		System.out.print("Valeur de l'expression " + exp.toStringInfixe() + " -- attendu == 1.878005213: ");
		System.out.println(exp.evaluer(env));
	}
}
