package mandala;

import mandala.filter.Filter;
import mandala.visualizer.Visualizer;

public interface Rasterizer {
	public Visualizer<Integer> renderScene(Viewport viewport, 
											VirtualCamera camera, 
											Filter<Double> postFilter, 
											Visualizer<Double> visualizer);

}
