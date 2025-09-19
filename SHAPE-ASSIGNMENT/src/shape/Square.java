package shape;

public class Square {

private double s;

public Square(double s) {
	super();
	this.s = s;
}

public double calArea(){
	return s*s;
			}
public double calPerimeter(){
	return s*4;
			}

}
