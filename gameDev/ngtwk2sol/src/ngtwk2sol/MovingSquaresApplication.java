package ngtwk2sol;
import java.awt.*;
import javax.swing.*;
//implement runnable for the thread
public class MovingSquaresApplication extends JFrame implements Runnable{
	//member data (static so it can be referenced from methods, final - doesnt change)
	private static final Dimension WindowSize = new Dimension(600,600);
	private static final int NUMGAMEOBJECTS = 30;
	private GameObject[] GameObjectsArray=new GameObject[NUMGAMEOBJECTS];
	private boolean isInitialised=false;
	
	//constructor
	public MovingSquaresApplication() {
		this.setTitle("Thread animation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//create and set up window
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width/2-WindowSize.width/2;
		int y = screenSize.height/2-WindowSize.height/2;
		setBounds(x,y,WindowSize.width,WindowSize.height);
		setVisible(true);
		
		//create and initialise game objects
		for(int i=0; i<NUMGAMEOBJECTS; i++) {
			GameObjectsArray[i] = new GameObject();
		}
		isInitialised = true;
		
		//create and start new thread for THIS application
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() { //thread entry point
		while(true) {
			//sleep for 1/50 sec
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {}
			
			//animate game objects
			for(int i=0; i<NUMGAMEOBJECTS; i++) GameObjectsArray[i].move();
			
			//force a repaint to show theyve moved
			this.repaint();
		}
	}
	//paint method 
	public void paint(Graphics g) {
		if(!isInitialised) return;
		//else paint canvas white
		g.setColor(Color.white);
		g.fillRect(0, 0, WindowSize.width, WindowSize.height);
		//paint squares
		for(int i=0; i<NUMGAMEOBJECTS;i++) GameObjectsArray[i].paint(g);
	}
	//application entry point
	public static void main(String[] args) {
		MovingSquaresApplication w = new MovingSquaresApplication();
	}
}
