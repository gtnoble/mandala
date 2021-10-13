package mandala.filter;

import mandala.visualizer.Visualizer;

public class NullFilter extends Filter {

	@Override
	public Visualizer filter(Visualizer inputVisualizer) {
		return inputVisualizer;
	}

}
