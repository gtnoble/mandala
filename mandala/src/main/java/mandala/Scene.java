package mandala;

import org.apache.commons.math3.complex.Complex;

public class Scene {
	Complex sceneCenter;
	int xScreenDimension, yScreenDimension;
	double zoom;
	
	public Scene(Complex sceneCenter, int xScreenDimension, int yScreenDimension, double zoom) {
		this.sceneCenter = sceneCenter;
		this.xScreenDimension = xScreenDimension;
		this.yScreenDimension = yScreenDimension;
		this.zoom = zoom;
	}
	
	public int getScreenWidth() {
		return xScreenDimension;
	}
	
	public int getScreenHeight() {
		return yScreenDimension;
	}
	
	public Complex[][] imageLayout() {
		
		
		Complex[][] layout = new Complex[xScreenDimension][yScreenDimension];
		
		for(int i = 0; i < xScreenDimension; i++) {
			for(int j = 0; j < yScreenDimension; j++) {
				layout[i][j] = screenToSceneCoords(i, j);
			}
		}
		
		return layout;
		
		
	}
	
	private double screenToCartesianX(int x) {
		return((double) x - 0.5 * ((double) xScreenDimension - 1));
	}
	
	private double screenToCartesianY(int y) {
		return((double) y - 0.5 * ((double) yScreenDimension - 1));
	}
	
	public double getInterPixelDistance() {
		return zoom / (double) yScreenDimension;
	}
	
	private Complex screenToSceneCoords(int x, int y) {
		
		double sceneRealUncentered = screenToCartesianX(x) * getInterPixelDistance();
		double sceneImagUncentered = screenToCartesianY(y) * getInterPixelDistance();
		
		return(new Complex(sceneRealUncentered, sceneImagUncentered).add(sceneCenter));
	}

}
