package mandala;

import java.util.function.IntFunction;
import java.util.function.LongFunction;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Viewport {
int ViewportXDimension, ViewportYDimension;

public Viewport(int xDim, int yDim) {
	this.ViewportXDimension = xDim;
	this.ViewportYDimension = yDim;
}

public int getXDimension() {
	return this.ViewportXDimension;
}

public int getYDimension() {
	return this.ViewportYDimension;
}

public Stream<XYPoint<Integer>> streamPixelCoordinates() {
	
	LongFunction<XYPoint<Integer>> lengthToXY = (input) -> {
		int x = (int) (input % getXDimension());
		int y = (int) (input / getXDimension());
		return new XYPoint<Integer>(x, y);
	};
	
	long totalNumberOfPixels = (((long) getXDimension()) * ((long) getYDimension()));
	
	return LongStream.rangeClosed(0, totalNumberOfPixels - 1).mapToObj(lengthToXY);
}

}
