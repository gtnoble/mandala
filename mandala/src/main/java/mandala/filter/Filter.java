package mandala.filter;

import mandala.visualizer.Visualizer;

public abstract class Filter {
	
	public abstract Visualizer filter(Visualizer inputVisualizer);
	
	public CompositeFilter chain(Filter inputFilter) {
		return new CompositeFilter(this, inputFilter);
	}
	
}
