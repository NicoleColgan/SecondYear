package ngtwk3sol;
import javax.swing.*; //JFrame
import java.awt.*; //Key listner
import java.awt.event.*;
public class InvadersApplication extends JFrame implements Runnable, KeyListener{
	//member variables
	private static String workingDirectory;
	private static boolean graphicsInitialised=false;
	private static final Dimension WINDOWSIZE= new Dimension(600,600);
	private static final int NUMALIENS=30;;
	private Sprite2D[] AliensArray = new Sprite2D[NUMALIENS];
	private Sprite2D playerShip;
	
	//Constructor
	public InvadersApplication() {
		//display window centered on screen
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x= screensize.width/2-WINDOWSIZE.width/2;
		int y = screensize.height/2-WINDOWSIZE.height/2;
		setBounds(x,y,WINDOWSIZE.width,WINDOWSIZE.height);
		setVisible(true);
		this.setTitle("Space invaders application");
		
		//load image from disk
		ImageIcon icon = new ImageIcon(workingDirectory+"\\alien_ship_2.png");
		Image alienImage=icon.getImage(); //convert icon to an image
		
		//create and initialise some aliens passing them the image
		for(int i=0; i<NUMALIENS; i++) {
			AliensArray[i]=new Sprite2D(alienImage);
		}
		//create and initialise player spaceship
		icon= new ImageIcon(workingDirectory+"\\player_ship.png");
		Image shipImage=icon.getImage();
		playerShip = new Sprite2D(shipImage);
		playerShip.setPosition(300, 530);
		
		//create and start thread
		Thread t = new Thread(this);
		t.start();
		
		//add key listener to this so we can use keys to move alien spaceship
		addKeyListener(this);
		//now its safe to paint the image
		graphicsInitialised=true;
	}
	
	//Thread entry point
	public void run() {
		while(true) { //game loop
			//sleep for 1/50 of a second
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {}
			
			//animate game objects (aliens)
			for (int i=0; i<NUMALIENS; i++) {
				AliensArray[i].moveEnemy();
			}
			playerShip.movePlayer(); //nothing will change if the speed hasnt changed
			
			this.repaint(); //force repaint
		}
	}
	
	//Three Keyboard Event-Handler functions
	public void keyPressed(KeyEvent e) { //move left or right
		if (e.getKeyCode()==KeyEvent.VK_LEFT) playerShip.setXSpeed(-4);
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT) playerShip.setXSpeed(4);
	}
	public void keyReleased(KeyEvent e) {
		//make sure we only account for arrow keys 
		if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
			playerShip.setXSpeed(0);
	}
	public void keyTyped(KeyEvent e) {} //not needed here
	
	//aplications paint method
	public void paint(Graphics g) {
		//dont try to draw unless objects are initialised
		if(graphicsInitialised) {
			//clear canvas with a big black rectangle
			g.setColor(Color.black);
			g.fillRect(0, 0, WINDOWSIZE.width, WINDOWSIZE.height);
			
			//draw game objects
			for (int i=0; i<NUMALIENS; i++) {
				AliensArray[i].paint(g);
			}
			playerShip.paint(g);
		}
	}
	//aplication entry point
	public static void main (String[] args) {
		workingDirectory=System.getProperty("user.dir");
		InvadersApplication w = new InvadersApplication();
	}
	
}
