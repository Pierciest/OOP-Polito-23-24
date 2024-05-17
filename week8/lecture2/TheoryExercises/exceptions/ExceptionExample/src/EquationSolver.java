
public class EquationSolver {
	private double a;
	private double b;
	private double c;
	public double getA() {
		return a;
	}
	public double getB() {
		return b;
	}
	public double getC() {
		return c;
	}
	public void setA(double a) {
		this.a = a;
	}
	public void setB(double b) {
		this.b = b;
	}
	public void setC(double c) {
		this.c = c;
	}

	public Double[] solve() throws  NegativeDiscriminantException, NullCoefficientException {
		double discriminant=b*b-4*a*c;
		
        //Handle the two possible excpetions
		if(discriminant<0){
			throw new NegativeDiscriminantException();
		}

		if(a==0){
			throw new NullCoefficientException("This is not a second degree equation");
		}


		
		
		double root1=(-b+Math.sqrt(discriminant)/(2*a));
		double root2=(-b-Math.sqrt(discriminant)/(2*a));
		
		return new Double[]{root1, root2};
	}
	
}
