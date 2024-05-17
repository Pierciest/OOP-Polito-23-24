public class App {
    public static void main(String[] args) {
    
		EquationSolver eq=new EquationSolver(); //ax^2+bx+c=0
		eq.setA(0); // 0 not a second degree equation
		eq.setB(3); //- 2 no real roots
		eq.setC(2);

		try{
			Double[] roots = eq.solve();
			System.out.println("Roots of the equation are " + roots[0] + ", " + roots[1]);

		}

		catch(NegativeDiscriminantException nde){
			System.out.print(nde);
			System.out.print("Do you know complex numbers?");
		}

		catch(NullCoefficientException nce){
			System.out.print(nce);
			//Solve the first degree equation
			try{
				double solutionFirstDegree = solveFirstDegree(eq.getB(), eq.getA());
				System.out.println("\nThe answer of the first degree equation is " + solutionFirstDegree);
			}
			catch(Exception nce2){
				System.out.println(nce2);
			}
		}

		catch(Exception e){
			System.out.println(e);
			
		}
    }
	static double solveFirstDegree(double b, double c) throws NullCoefficientException{
		if(b == 0){
			if(c==0){
				throw new NullCoefficientException("Indeterminate equation");
			}
			throw new NullCoefficientException("Impossible equation");
		}
		return (double) -c/b;
	}
}
