package mandala;

public class XYZPoint<T extends Number, U extends Number> {
	
	XYPoint<T> xYComponent;
	U zComponent;
	
	public XYZPoint(XYPoint<T> xYComponent, U zComponent) {
		this.xYComponent = xYComponent;
		this.zComponent = zComponent;
	}
	
	public XYZPoint(T xComponent, T yComponent, U zComponent) {
		this(new XYPoint<T>(xComponent, yComponent), zComponent);
	}
	
	public XYPoint<T> getXY() {
		return xYComponent;
	}
	
	public U getZ() {
		return zComponent;
	}

}
