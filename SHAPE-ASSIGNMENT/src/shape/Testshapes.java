package shape;

import java.util.Scanner;

public class Testshapes {

	
	private static double r;

	public static void main(String[] args) {
		Scanner hh=new Scanner(System.in);
		
		char choice;
		do{
			System.out.println("SELECT THE SHAPE");
			System.out.println("1.RECTANGLE");
			System.out.println("2.CIRCLE");
			System.out.println("3.SQUARE");
			System.out.println("4.TRIANGLE");
			System.out.println("select shape :");
			
			int choic=hh.nextInt();
		

		switch (choic){
		case 1:
			System.out.println("Enter the Length");
			double l=hh.nextDouble();
			System.out.println("Enter the Width");
			double w =hh.nextDouble();
			Rectangle h=new Rectangle(l,w);
			System.out.println("What do you like to calculate");
			System.out.println("1.Area ?");
			System.out.println("2.Perimeter ?");
			System.out.println("Your choice :");
			
			int jj=hh.nextInt();
			
			if(jj==1){
				System.out.println("Area of Rectangle :" +h.CalArea());
			}else if(jj==2){
				System.out.println("Perimeter of Rectangle :" +h.CalPerimeter());
				
			}
			else {
				System.out.println("Invalid selection ");
			}
			break;
		case 2:
			System.out.println("Enter the Radius");
			double kk=hh.nextDouble();
			Circle ll=new Circle(kk);
			System.out.println("What do you like to calculate");
			System.out.println("1.Area ?");
			System.out.println("2.Circumference ?");
			System.out.println("Your choice :");
			int uu=hh.nextInt();
			if(uu==1){
				System.out.println("Area of Circle :" +ll.calArea());
			}else if(uu==2){
				System.out.println("Circumference of Circle :" +ll.calcirc());
				
			}
			else {
				System.out.println("Invalid selection ");
			}
			break;
		case 3:
			System.out.println("Enter the side");
			double k=hh.nextDouble();
			Square tt=new Square(k);
			System.out.println("What do you like to calculate");
			System.out.println("1.Area ?");
			System.out.println("2.Primeter ?");
			System.out.println("Your choice :");
			int oo=hh.nextInt();
			if(oo==1){
				System.out.println("Area of Circle :" +tt.calArea());
			}else if(oo==2){
				System.out.println("Circumference of Circle :" +tt.calPerimeter());
				
			}
			else {
				System.out.println("Invalid selection ");
			}
		case 4:
			System.out.println("Enter the Base");
			double b=hh.nextDouble();
			System.out.println("Enter the Height");
			double hf=hh.nextDouble();
			Triangle dh=new Triangle(b,hf);
			System.out.println("What do you like to calculate");
			System.out.println("1.Area ?");
			System.out.println("2.Perimeter ?");
			System.out.println("Your choice :");
			
			int j=hh.nextInt();
			
			if(j==1){
				System.out.println("Area of Rectangle :" +dh.calArea());
			}else if(j==2){
				System.out.println("Perimeter of Rectangle :" +dh.calPerimeter());
				
			}
			else {
				System.out.println("Invalid selection ");
			}
			break;
			default:
				
				System.out.println("Invalid selection");
				break;
		}
		System.out.println("Would you like to continue? (Y/N)");
		choice=hh.next().charAt(0);
		}
		while(choice=='Y');
		hh.close();
	}
 
}
