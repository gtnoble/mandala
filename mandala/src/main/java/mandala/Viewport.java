package mandala;

import java.util.function.LongFunction;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Viewport {
int ViewportXDimension, ViewportYDimension, offsetX, offsetY;

public Viewport(int xDim, int yDim, int offsetX, int offsetY) {
	this.ViewportXDimension = xDim;
	this.ViewportYDimension = yDim;
	this.offsetX = offsetX;
	this.offsetY = offsetY;
}

public Viewport(int xDim, int yDim) {
	this(xDim, yDim, 0, 0);
}

public int getXDimension() {
	return this.ViewportXDimension;
}

public int getYDimension() {
	return this.ViewportYDimension;
}

public Stream<XYPoint<Integer>> streamPixelCoordinates() {
	
	LongFunction<XYPoint<Integer>> lengthToXY = (input) -> {
		int x = (int) (input % getXDimension()) + offsetX;
		int y = (int) (input / getXDimension()) + offsetY;
		return new XYPoint<Integer>(x, y);
	};
	
	long totalNumberOfPixels = (((long) getXDimension()) * ((long) getYDimension()));
	
	return LongStream.rangeClosed(0, totalNumberOfPixels - 1).mapToObj(lengthToXY);
}

}
