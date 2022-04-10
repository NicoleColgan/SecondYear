package ngtwk4sol;
import java.awt.*;
public class Sprite2D { //superclass for aliens and spaceShips
	//member data 
	protected static double x;
	protected static double y;
	protected static double xSpeed=0;
	protected static Image myImage;
	protected Image myImage2;
	int framesDrawn=0;
	//static member data
	protected static int winWidth;
	
	//constructor
	public Sprite2D(Image i, Image i2) {
		myImage=i;
		myImage2=i2;
	}
	public static void setPosition(double xx, double yy) {
		x=xx;
		y=yy;
	}
	public void setXSpeed(double dx) {
		xSpeed=dx;
	}
	public void paint(Graphics g) {
		framesDrawn++;
		if(framesDrawn%100<50) { //redraw every 50 frames
			g.drawImage(myImage, (int)x, (int)y, null);
		}
		else
			g.drawImage(myImage2, (int)x, (int)y, null);
		}
	public static void setWinWidth(int w) {
		winWidth=w;
	}

}
