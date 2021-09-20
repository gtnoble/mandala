package mandala;

import org.apache.commons.math3.complex.Complex;

import mandala.visualizer.Visualizer;

public class BasicRenderer implements Renderer {
	
	public BasicRenderer() {}

	public Image renderScene(Scene scene, Visualizer visualizer) {
		Complex[][] imageLayout = scene.imageLayout();
		int width = scene.getScreenWidth();
		int height = scene.getScreenHeight();
		
		Image image = new Image(width, height);
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				double value = visualizer.value(imageLayout[x][y]);
				image.setPixel(x, y, value);
			}
		}
		return image;
	}

}
