package mandala;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

import mandala.visualizer.Visualizer;

public class ParallelBasicRenderer implements Rasterizer {
	
	public ParallelBasicRenderer() {};

	@Override
	public Image renderScene(Viewport viewport, Visualizer visualizer) {
		// TODO Auto-generated method stub
		Image image;
		
		image = new Image(viewport);
		
		viewport.streamPixelCoordinates().map((point) -> visualizer.value(point)).forEach(())

		return image;
	}

}
