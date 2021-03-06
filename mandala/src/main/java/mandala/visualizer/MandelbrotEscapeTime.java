package mandala.visualizer;

import java.util.HashSet;

import org.apache.commons.math3.complex.Complex;

import mandala.XYPoint;

public class MandelbrotEscapeTime implements Visualizer{
	int maxIterations;
	double interPixelDistance;
	
	public MandelbrotEscapeTime(int maxIterations, double interPixelDistance) {
		this.maxIterations = maxIterations;
		this.interPixelDistance = interPixelDistance;
	}
	
	public double getInterPixelDistance() {
		return interPixelDistance;
	}
	
	public double value(XYPoint<Double> point) {
		int iterationCount;
		Complex location = point.toComplex();
		Complex z = new Complex(0, 0);
		
		HashSet<Complex> previousZ = new HashSet<Complex>();
		previousZ.add(z);
		
		if(bulbCheck(location)) {
			iterationCount = maxIterations;
			return( (double) iterationCount);
		}
		
		for(iterationCount = 0; iterationCount <= maxIterations; iterationCount++) {
			z = z.multiply(z).add(location);
			
			if(z.abs() >= 2.0) {
				break;
			}

			if(previousZ.contains(z)) {
				iterationCount = maxIterations;
				break;
			}
			else {
				previousZ.add(z);
			}
			
			
		}
		
		return((double) iterationCount);
	}
	
	private boolean bulbCheck(Complex location) {
		double x = location.getReal();
		double y = location.getImaginary();
		
		double q = Math.pow((x - .25), 2) + Math.pow(y, 2);
		return((q * (q + (x - .25))) <= (.25 * Math.pow(y, 2)));
	}
}
