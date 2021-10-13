package mandala;

import java.util.stream.Stream;

public class PixelPipeline {
	Viewport viewport;
	Stream<XYZPoint<Integer, Double>> pixelStream;
	
	public PixelPipeline(Viewport viewport, Stream<XYZPoint<Integer, Double>> pixelStream) {
		this.viewport = viewport;
		this.pixelStream = pixelStream;
	}
	
	public int getRasterXDimension() {
		return this.viewport.ViewportXDimension;
	}
	
	public int getRasterYDimension() {
		return this.viewport.ViewportYDimension;
	}
	
	public Stream<XYZPoint<Integer, Double>> getPixelStream() {
		return this.pixelStream;
	}

}
