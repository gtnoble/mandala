package mandala;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

import mandala.visualizer.Visualizer;

public class ParallelBasicRenderer implements Renderer {
	
	public ParallelBasicRenderer() {};

	@Override
	public Image renderScene(Scene scene, Visualizer visualizer) {
		// TODO Auto-generated method stub
		Complex imageLayout[][] = scene.imageLayout();
		double[][] imagePixels;
		Image image;
		
		imagePixels = Arrays.stream(imageLayout).parallel().map(
				row -> {
					//Double[] resultRow;
					//resultRow = Arrays.stream(row).map(location -> visualizer.value(location)).toArray(Double[]::new);
					double[] resultRow = new double[scene.getScreenWidth()];
					for(int i = 0; i < scene.getScreenWidth(); i++) {
						resultRow[i] = visualizer.value(row[i]);
					}
					return resultRow;
				} 
				).toArray(double[][]::new);
		
		image = new Image(imagePixels, scene.getScreenWidth(), scene.getScreenHeight());

		return image;
	}

}
