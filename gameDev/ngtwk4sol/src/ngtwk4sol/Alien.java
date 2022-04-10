package ngtwk4sol;

import java.awt.Image;
import java.awt.Graphics;
public class Alien extends Sprite2D {
	protected boolean isAlive=true;
	
	public Alien(Image i, Image i2) {
		super(i,i2);
	}
	public void paint(Graphics g) {
		if(isAlive)
			super.paint(g);
	}
	//public interface
	public boolean move() {
		x+=xSpeed;
		//direction reversal needed?
		if(x<=0 || x >=winWidth-myImage.getWidth(null)) //offscreen
			return true;
		else 
			return false;
	}
	
	public  void reverseDirection() {
		xSpeed=-xSpeed;
		y+=20;
	}
	

}
