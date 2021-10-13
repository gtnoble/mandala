package mandala.filter;

import mandala.visualizer.Visualizer;

public class CompositeFilter<T extends Number> extends Filter<T>{

	Filter<T> innerFilter; 
	Filter<T> outerFilter;
	
	CompositeFilter(Filter<T> innerFilter, Filter<T> outerFilter) {
		this.innerFilter = innerFilter;
		this.outerFilter = outerFilter;
	}
	
	@Override
	public Visualizer<T> filter(Visualizer<T> inputVisualizer) {
		return outerFilter.filter(innerFilter.filter(inputVisualizer));
	}

}
