package mandala;

import java.util.stream.Stream;


import mandala.filter.Filter;
import mandala.filter.NullFilter;
import mandala.visualizer.Visualizer;

public class ParallelBasicRasterizer implements Rasterizer {
	
	public ParallelBasicRasterizer() {};

	@Override
	public PixelPipeline renderScene(Viewport viewport, VirtualCamera camera, Filter postFilter, Visualizer visualizer) {

		if(postFilter == null) postFilter = new NullFilter();
		
		CoordinateTransformation viewportCoordinateTransformer = camera.sceneToViewportCoordinateTransformer(viewport);
		Visualizer transformedVisualizer = (scenePt) -> visualizer.value(viewportCoordinateTransformer.transform(scenePt));
		Visualizer signalChain = postFilter.filter(transformedVisualizer);
		Stream<XYZPoint<Integer, Double>> pixelStream = viewport
							.streamPixelCoordinates().parallel()
							.map((point) -> new XYZPoint<Integer, Double>(point, signalChain.value(point.toDoublePoint())));

		return new PixelPipeline(viewport, pixelStream);
	}

}
