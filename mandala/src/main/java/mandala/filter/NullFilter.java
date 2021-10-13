package mandala.filter;

import mandala.visualizer.Visualizer;

public class NullFilter<T extends Number> extends Filter<T> {

	@Override
	public Visualizer<T> filter(Visualizer<T> inputVisualizer) {
		return inputVisualizer;
	}

}
