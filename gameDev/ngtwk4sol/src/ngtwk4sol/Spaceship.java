package ngtwk4sol;
import java.awt.Image;
public class Spaceship extends Sprite2D{
	private static Image bulletImage;
	
	public Spaceship(Image i, Image bullet) {
		super(i,i); //invoke constructor of superclass
		bulletImage=bullet;
	}
	
	public static void move() {
		//apply current speed
		x+=xSpeed;
		
		//stop movement at screen edge
		if(x<=0) {
			x=0;
			xSpeed=0;
		}
		else if(x>=winWidth-myImage.getWidth(null)) {
			x=winWidth-myImage.getWidth(null);
			xSpeed=0;
		}
	}
	
	//method to handle shooting
	public static PlayerBullet shootBullet() { 
		//add new nullet to the list
		PlayerBullet b = new PlayerBullet(bulletImage);
		b.setPosition(x+54/2,y); 
		return b;
	}
	
}
