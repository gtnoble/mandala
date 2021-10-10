package mandala.visualizer;

import org.apache.commons.math3.complex.Complex;

import mandala.XYPoint;

public class MandelbrotExteriorDistance implements Visualizer {

	int maxIterations;
	public MandelbrotExteriorDistance(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	
	double computeExteriorDistance(Complex iteratedPolynomialValue, 
									Complex iteratedPolynomialDerivativeValue, 
									int iterations,
									Complex Location) {
		
		if(iterations == 0) return iteratedPolynomialValue.abs() * Math.log(iteratedPolynomialValue.abs()) / 
													(2 * iteratedPolynomialDerivativeValue.abs());

		else {
			Complex nextPolynomialValue = iteratedPolynomialValue.multiply(iteratedPolynomialValue).add(Location);
			Complex nextDerivativeValue = iteratedPolynomialValue.multiply(iteratedPolynomialDerivativeValue).multiply(2).add(1);
			return computeExteriorDistance(nextPolynomialValue, nextDerivativeValue, iterations--, Location);
		}
	}
	
	@Override
	public double value(XYPoint<Double> point) {
		Complex polynomialInitialValue = new Complex(0, 0);
		Complex derivativeInitialValue = new Complex(1, 0);
		Complex location = point.toComplex();
		
		return computeExteriorDistance(polynomialInitialValue, derivativeInitialValue, maxIterations, location);
	}

}
