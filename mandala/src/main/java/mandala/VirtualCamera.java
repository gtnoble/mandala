package mandala;

public class VirtualCamera {
	XYPoint<Double> cameraLocation;
	int xScreenDimension, yScreenDimension;
	double zoom;
	
	public VirtualCamera(XYPoint<Double> cameraLocation, double zoom) {
		this.cameraLocation = cameraLocation;
		this.zoom = zoom;
	}
	
	private double screenToCartesianCoordinates(double screenCoordinate, int nPixelsAlongDimension) {
		return((double) screenCoordinate - 0.5 * ((double) nPixelsAlongDimension - 1));
	}
	
	public double getInterPixelDistance(int yScreenDimension) {
		return zoom / (double) yScreenDimension;
	}
	
	// Transforms screen pixel coordinates to corresponding coordinates in the scene.
	public CoordinateTransformation sceneToViewportCoordinateTransformer(Viewport viewport) {

		double interPixelDistance = getInterPixelDistance(viewport.ViewportYDimension);

		CoordinateTransformation transformer = (inputPoint) -> {
			double sceneX = screenToCartesianCoordinates(inputPoint.x, viewport.ViewportXDimension) * interPixelDistance + cameraLocation.getX();
			double sceneY = screenToCartesianCoordinates(inputPoint.y, viewport.ViewportYDimension) * interPixelDistance + cameraLocation.getY();
			return new XYPoint<Double>(sceneX, sceneY);
		};
		
		return transformer;
	}

}
