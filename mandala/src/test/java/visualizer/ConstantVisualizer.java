package visualizer;

import mandala.XYPoint;
import mandala.visualizer.Visualizer;

public class ConstantVisualizer implements Visualizer {
	
	double constantValue;
	
	public ConstantVisualizer(double constantValue) {
		this.constantValue = constantValue;
		
	}
	
	@Override
	public double value(XYPoint<Double> point) {
		return constantValue;
	}

}
