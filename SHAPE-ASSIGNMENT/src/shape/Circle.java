package shape;

public class Circle {

	private double r;

	public Circle(double r) {
		super();
		this.r = r;
	}
	public double calArea(){
		return Math.PI*(r*r);
				}
	public double calcirc(){
		return 2*Math.PI*r;
				}
}
