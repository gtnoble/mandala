package visualizer;

import org.apache.commons.math3.complex.Complex;

import mandala.visualizer.Visualizer;

public class ConstantVisualizer implements Visualizer {
	
	double constantValue;
	
	public ConstantVisualizer(double constantValue) {
		this.constantValue = constantValue;
		
	}
	
	public double getInterPixelDistance() {
		return 1;
	}

	@Override
	public double value(Complex location) {
		return constantValue;
	}

}
