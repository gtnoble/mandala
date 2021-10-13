package mandala;

import java.util.Objects;

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
	
	public boolean equals(XYPoint<?> point) {
		if (point == null) return false;
		if (point.getX().equals(x) && point.getY().equals(y)) return true;
		else return false;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof XYPoint<?>) {
			return this.equals((XYPoint<?>) other);
		}
		else return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public Complex toComplex() {
		return new Complex((double) this.x, (double) this.y);
	}
	
	public XYPoint<Double> toDoublePoint() {
		return new XYPoint<Double>(x.doubleValue(), y.doubleValue());
	}
	
}
