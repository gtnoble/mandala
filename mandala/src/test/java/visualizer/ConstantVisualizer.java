package visualizer;

import org.apache.commons.math3.complex.Complex;

import mandala.visualizer.Visualizer;

public class ConstantVisualizer implements Visualizer {

	@Override
	public double value(Complex location) {
		return 1;
	}

}
