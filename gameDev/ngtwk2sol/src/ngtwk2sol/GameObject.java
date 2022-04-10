package ngtwk2sol;
import java.awt.*;
public class GameObject {
	//member data
	private double x,y; //coords
	private Color c; //each square has a colour
	
	public GameObject() {
		x= Math.random()*600;  //random coords
		y= Math.random()*600;
		int r = (int)(Math.random()*256);  //random colours
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);
		c = new Color(r,g,b);
	}
	public void move() {
		x+=10*(Math.random()-Math.random()); //small number and *10 to make movement visible
		y+=10*(Math.random()-Math.random());
	}
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect((int)x, (int)y,40,40);
		
	}
}
