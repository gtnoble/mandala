package mandala;

import org.apache.commons.math3.complex.Complex;

public class XYPoint<T extends Number> {
	T x;
	T y;
	
	public XYPoint(T x, T y) {
		this.x = x;
		this.y = y;
	}
	
	public XYPoint(T[] coordinates) {
		this.x = coordinates[0];
		this.y = coordinates[1];
	}
	
	public T getX() {
		return this.x;
	}
	
	public T getY() {
		return this.y;
	}
	
	public Complex toComplex() {
		return new Complex((double) this.x, (double) this.y);
	}
	
	public XYPoint<Double> toDoublePoint() {
		return new XYPoint<Double>((double) x, (double) y);
	}
	
}
