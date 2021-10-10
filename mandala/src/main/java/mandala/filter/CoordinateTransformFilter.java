package mandala.filter;

import mandala.CoordinateTransformation;
import mandala.visualizer.Visualizer;

public class CoordinateTransformFilter extends Filter {
	CoordinateTransformation transformation;
	
	public CoordinateTransformFilter(CoordinateTransformation transformation) {
		this.transformation = transformation;
	}

	@Override
	public Visualizer filter(Visualizer inputVisualizer) {
		
		return (coordinates) -> inputVisualizer.value(transformation.transform(coordinates));
	}

}
