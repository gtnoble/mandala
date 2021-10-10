package mandala;

import mandala.visualizer.Visualizer;

public interface Rasterizer {
	public Image renderScene(Viewport viewport, Visualizer visualizer);

}
