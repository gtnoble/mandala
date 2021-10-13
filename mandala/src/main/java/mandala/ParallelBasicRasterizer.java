package mandala;

import mandala.filter.Filter;
import mandala.filter.NullFilter;
import mandala.visualizer.Visualizer;

public class ParallelBasicRasterizer implements Rasterizer {
	
	public ParallelBasicRasterizer() {};

	@Override
	public Visualizer<Integer> renderScene(Viewport viewport, VirtualCamera camera, Filter<Double> postFilter, Visualizer<Double> visualizer) {

		if(postFilter == null) postFilter = new NullFilter<Double>();
		
		CoordinateTransformation viewportCoordinateTransformer = camera.sceneToViewportCoordinateTransformer(viewport);
		Visualizer<Double> transformedVisualizer = (scenePt) -> visualizer.value(viewportCoordinateTransformer.transform(scenePt));
		Visualizer<Double> signalChain = postFilter.filter(transformedVisualizer);
		Visualizer<Integer> raster = (point) -> signalChain.value(point.toDoublePoint());

		return raster;
	}

}
