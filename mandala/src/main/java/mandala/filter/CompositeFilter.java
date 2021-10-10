package mandala.filter;

import mandala.visualizer.Visualizer;

public class CompositeFilter extends Filter{

	Filter innerFilter; 
	Filter outerFilter;
	
	CompositeFilter(Filter innerFilter, Filter outerFilter) {
		this.innerFilter = innerFilter;
		this.outerFilter = outerFilter;
	}
	
	@Override
	public Visualizer filter(Visualizer inputVisualizer) {
		return outerFilter.filter(innerFilter.filter(inputVisualizer));
	}

}
