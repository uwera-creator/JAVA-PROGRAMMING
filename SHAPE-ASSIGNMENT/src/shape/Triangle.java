package shape;

public class Triangle {

private double h;
private double b;
private double hyp;
public Triangle(double h, double b) {
	super();
	this.h = h;
	this.b = b;
	double r=(b*b)+(h*h);
	this.hyp=Math.sqrt(r);
	
			}
public double calArea(){
	return this.b*this.h/2;
			}
public double calPerimeter(){
	return this.b*this.h+this.hyp;
			}


}
