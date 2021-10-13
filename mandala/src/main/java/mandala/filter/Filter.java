package mandala.filter;

import mandala.visualizer.Visualizer;

public abstract class Filter<T extends Number> {
	
	public abstract Visualizer<T> filter(Visualizer<T> inputVisualizer);
	
	public CompositeFilter<T> chain(Filter<T> inputFilter) {
	return new CompositeFilter<T>(this, inputFilter);
	}
	
}
