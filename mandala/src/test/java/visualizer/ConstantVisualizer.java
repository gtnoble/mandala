package visualizer;

import org.apache.commons.math3.complex.Complex;

import mandala.visualizer.Visualizer;

public class ConstantVisualizer implements Visualizer {
	
	double constantValue;
	
	public ConstantVisualizer(double constantValue) {
		this.constantValue = constantValue;
		
	}

	@Override
	public double value(Complex location) {
		return constantValue;
	}

}
