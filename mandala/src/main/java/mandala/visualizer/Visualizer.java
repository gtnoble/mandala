package mandala.visualizer;

import mandala.XYPoint;

public interface Visualizer<T extends Number> {
	public double value(XYPoint<T> point);
	
}
