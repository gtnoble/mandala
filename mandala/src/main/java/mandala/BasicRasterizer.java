package mandala;

import mandala.visualizer.Visualizer;

public class BasicRasterizer implements Rasterizer {
	
	public BasicRasterizer() {}

	public Image renderScene(Viewport viewport, Visualizer visualizer) {
		int width = viewport.getXDimension();
		int height = viewport.getYDimension();
		
		Image image = new Image(viewport);
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				double value = visualizer.value(new XYPoint<Double>((double) x, (double) y));
				image.setPixel(x, y, value);
			}
		}
		return image;
	}

}
