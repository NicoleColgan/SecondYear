package ngtwk1sol;

import java.awt.*; //needed for dimension, colour etc. 

import javax.swing.JFrame; //needed for creating a JFrame

public class MyApplication extends JFrame{
	//window size for JFrame
	private static final Dimension windowSize = new Dimension(600,600);
	
	public MyApplication() {
		//create and set up the window
		this.setTitle("My grid of squares");
		//allow window to be closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//display the window centered on the screen
		//actual screensize of computer
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x= screensize.width/2-windowSize.width/2; //centre width
		int y= screensize.height/2-windowSize.height/2;
		//set bounds for JFrame (starting positionx, starting posy, lenght, height)
		setBounds(x,y,windowSize.width,windowSize.height);
		//display JFrame
		setVisible(true);
	}
	public void paint(Graphics g) { //automatically called after contructor is called
		for(int x=50; x<600; x+=50) {
			for(int y=50; y<600; y+=50) {
				//colour value between 0-256
				int red = (int)(Math.random()*256);
				int green = (int)(Math.random()*256);
				int blue = (int)(Math.random()*256);
				Color c = new Color(red,green,blue);
				g.setColor(c);
				g.fillRect(x-20, y-20, 40, 40); // "-" to create gaps
			}
		}
	}
	public static void main(String[] args) {
		//needed to run the program and call construtor and subsequentially paint method
		MyApplication w = new MyApplication();
	}
}
