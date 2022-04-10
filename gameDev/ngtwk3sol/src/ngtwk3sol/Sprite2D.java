package ngtwk3sol;
import java.awt.*; //image 
public class Sprite2D {
	//member data
	private double x,y,xSpeed=0; //take default value of 0
	private Image myImage;
	
	//constructor
	public Sprite2D(Image i) {
		x=Math.random()*600; //initialise position to random value between 0 and 600
		y=Math.random()*600;
		myImage=i; //aliens appearance
	}
	//public interface
	public void moveEnemy() {
		x+=10*(Math.random()-Math.random());
		y+=10*(Math.random()-Math.random());
		if(x<0) x=0;
		else if(x>600) x=600;
		if(y<50) y=50; //keep aliens at the top of the screen
		else if(y>400) y=400;
	}
	public void setPosition(double xx, double yy) { //give player initial position
		x=xx;
		y=yy;
	}
	public void movePlayer(){
		x+=xSpeed;
	}
	public void setXSpeed(double dx) {  //change player speed/ direction
		xSpeed=dx;
	}
	public void paint(Graphics g) { //paint at given position
		g.drawImage(myImage, (int)x, (int)y,null);
	}
}
