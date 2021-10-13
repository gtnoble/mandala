package mandala;

import mandala.filter.Filter;
import mandala.visualizer.Visualizer;

public interface Rasterizer {
	public PixelPipeline renderScene(Viewport viewport, VirtualCamera camera, Filter postFilter, Visualizer visualizer);

}
