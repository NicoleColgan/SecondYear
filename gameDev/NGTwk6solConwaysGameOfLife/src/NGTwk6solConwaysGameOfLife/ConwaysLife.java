package NGTwk6solConwaysGameOfLife;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class ConwaysLife extends JFrame implements Runnable, MouseListener, MouseMotionListener{
	// member data
	private BufferStrategy strategy;
	private Graphics offscreenBuffer;
	//each cell (square) has an x and y index and each is either true (white) or false (black)
	/*
	 * now we have two buffers the front buffer and the back buffer, these allow us to make changes as we
	 * iterate through checking for neighbours
	 */
	private boolean gameState[][][] = new boolean[40][40][2];
	private int gameStateFrontBuffer = 0;
	private boolean isGameRunning = false;
	private boolean initialised = false;
	//for reading the file 
	private FileReader fr;
	private static String workingDirectory;
	
	// constructor
	public ConwaysLife () {
	 //Display the window, centred on the screen
	 Dimension ss = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	 int x = ss.width/2 - 400;
	 int y = ss.height/2 - 400;
	 setBounds(x, y, 800, 800);
	 setVisible(true);
	 this.setTitle("Conway's game of life");

	 // initialise double-buffering
	 createBufferStrategy(2);
	 strategy = getBufferStrategy();
	 offscreenBuffer = strategy.getDrawGraphics();

	 // register the Jframe itself to receive mouse events
	 addMouseListener(this);

	// initialise the game state
	 for (x=0;x<40;x++) {
		 for (y=0;y<40;y++) {
			 gameState[x][y][0]=gameState[x][y][1]=false;
		 }
	 }
	 // create and start our animation thread
	 Thread t = new Thread(this);
	 t.start();
	 
	 initialised = true;
	}
	
	// thread's entry point
	public void run() {
		while ( 1==1 ) {
			// 1: sleep for 1/5 sec
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) { }
			// 2: animate game objects 
			// 2: animate game objects
			 if (isGameRunning)
				 doOneEpochOfGame();
			// 3: force an application repaint
			this.repaint();
		}
	}
	
	//mouse motion listener methods
	public void mouseMoved(MouseEvent e) {
		//System.out.println("mOVED: "+e);
		
	}
	public void mouseDragged(MouseEvent e) {
		//System.out.println("drag: "+e);
		gameState[e.getX()/20][e.getY()/20][gameStateFrontBuffer]=true;
	}
	
	private void doOneEpochOfGame() {
		 // apply game rules to game state ‘front buffer’, copying results into ‘back buffer’
		 int front = gameStateFrontBuffer; //initially 0
		 /*
		  * back buffer is shown when front buffer isnt and has a different value for game state 
		  * i.e. if front buffer's game state is 1, backs is 0
		  * Modulus operator allows us to make it only either 0 or 1
		  */
		 int back = (front+1)%2; 
		 
		 for (int x=0;x<40;x++) {
			 for (int y=0;y<40;y++) {
				 // count the neighbours of cell x,y
				 int liveneighbours=0;
				 for (int xx=-1;xx<=1;xx++) {
					 for (int yy=-1;yy<=1;yy++) {
						 //if either one of the xx or yy is not 0, then its a neighbour
						 //if both were 0 we would be talking about the cell itself
						 if (xx!=0 || yy!=0) {
							 int xxx=x+xx;
							 if (xxx<0)
								 xxx=39; //go to other side of screen
							 else if (xxx>39)
								 xxx=0;
							 int yyy=y+yy;
							 if (yyy<0)
								 yyy=39;
							 else if (yyy>39)
								 yyy=0;
							 //if this neighbour is alive, increase number or live neighbours
							 if (gameState[xxx][yyy][front]) 
								 liveneighbours++;
						 }
					 }
				 }
				 
				 // apply rules for cell x,y
				 //apply changes to back buffer
				 if (gameState[x][y][front]) {
					 	// cell x,y was alive
					 // #1. Any live cell with fewer than two live neighbours dies
					 if (liveneighbours<2)
						 gameState[x][y][back] = false;
					 // #2. Any live cell with two or three live neighbours lives
					 else if (liveneighbours<4)
						 gameState[x][y][back] = true;
					 // #3. Any live cell with more than three live neighbours dies 
					 else
						 gameState[x][y][back] = false;
				 }
				 
				 else {
					 // cell x,y was dead
					 // #4. Dead cells with three live neighbours become live
					 if (liveneighbours==3)
						 gameState[x][y][back] = true;
					 else
						 gameState[x][y][back] = false;
				 }
			 }
		 }

		 // now flip the game state buffers
		 gameStateFrontBuffer = back;
		}
	
	private void randomiseGameState() {
		 for (int x=0;x<40;x++) {
			for (int y=0;y<40;y++) {
				//1/4 chance of being alive
				gameState[x][y][gameStateFrontBuffer]=(Math.random()<0.25);
			}
		 }
		}
	
	// mouse events which must be implemented for MouseListener
	 public void mousePressed(MouseEvent e) {
		 // determine which cell of the gameState array was clicked on
		 /*
		  * Theres 40 cells and each is 20 pixels wide so divide by 20 to get values
		  */
		 //if the game is not running, check if they pressed the buttons
		 if (!isGameRunning) {
			//if load is pressed load in a picture from a file
				if(e.getX()>=400 && e.getX()<=480 && e.getY()>=50 && e.getY()<=80) {
					
					String filename=workingDirectory + "\\inputText.txt";
					String textinput=null;
					
					try {
						BufferedReader reader = new BufferedReader(new FileReader(filename));
						do{
							try{
								textinput=reader.readLine();
								int textIndex=0;
								if(textinput!=null) {
									for(int i = 0;i<40;i++) {
										for(int j = 0;j<40;j++) {
											//if 1 then it will be painted, if 0 it won't 
											if(textinput.charAt(textIndex)=='1') gameState[i][j][gameStateFrontBuffer]=true;
											else gameState[i][j][gameStateFrontBuffer]=false;
											textIndex++;  //move and check next character
										}
									}
								}
							}
							catch(IOException e1) {} 
							
						}
						while(textinput!=null); //do while theres another character in the text file
						reader.close();
					}
					catch (IOException e1) {}
				}
				
				//save button was pressed
				if(e.getX()>=500 && e.getX()<=580 && e.getY()>=50 && e.getY()<=80) {
					String filename2=workingDirectory + "\\outputText.txt";
					
					try {
						String output="";
						BufferedWriter writer= new BufferedWriter(new FileWriter(filename2));
					
						//make the output text
						for(int i = 0;i<40;i++) {
							for(int j = 0;j<40;j++) {
								if(gameState[i][j][gameStateFrontBuffer])output+="1";
								else output+="0";
							}
						}
						writer.write(output);
						writer.close();
					} catch(IOException e2) {}
				}
				
			 // was the click on the 'start button'?
			 int x = e.getX();
			 int y = e.getY();
			 if (x>=15 && x<=85 && y>=40 && y<=70) {
				 isGameRunning=true;
				 return; //dont check for any other presses
			 }
			 // or on the 'random' button?
			 if (x>=115 && x<=215 && y>=40 && y<=70) {
				 randomiseGameState();
				 return;
			 }
		 }
		 
		 int x = e.getX()/20;
		 int y = e.getY()/20;
		 // toggle the state of the cell (i.e. if it was clicked turn it off or on)
		 gameState[x][y][gameStateFrontBuffer] =!gameState[x][y][gameStateFrontBuffer];
		 
		 // request an extra repaint, to get immediate visual feedback
		 this.repaint();
	 }
	 //dont need these methods right now
	 public void mouseReleased(MouseEvent e) { }
     public void mouseEntered(MouseEvent e) { }
	 public void mouseExited(MouseEvent e) { }
	 public void mouseClicked(MouseEvent e) { }
	 
	// application's paint method
	 public void paint(Graphics g) {
		 if (!initialised)
			 return;
		 
		 g = offscreenBuffer; // draw to offscreen buffer
		 // clear the canvas with a black rectangle
		 g.setColor(Color.BLACK);
		 g.fillRect(0, 0, 800, 800);
		 // redraw all game objects
		 g.setColor(Color.WHITE);
		// redraw all game objects
		 g.setColor(Color.WHITE);
		  for (int x=0;x<40;x++) {
			  for (int y=0;y<40;y++) {
				  if (gameState[x][y][gameStateFrontBuffer]) { //if its true (white) paint it
					  g.fillRect(x*20, y*20, 20, 20);
				  }
			  }
		  }
		  
		  if (!isGameRunning) {
			  // game is not running..
			  // draw a 'start button' as a rectangle with text on top
			  // also draw a 'randomise' button
			  g.setColor(Color.GREEN);
			  g.fillRect(15, 40, 70, 30);
			  g.fillRect(115, 40, 100, 30);
			  g.setFont(new Font("Times", Font.PLAIN, 24));
			  g.setColor(Color.BLACK);
			  g.drawString("Start", 22, 62);
			  g.drawString("Random", 122, 62);
			  //add load and save button
			  g.drawString("Load", 410, 75);
			  g.drawString("Save", 510, 75);
		  }
		  
	 // flip the buffers
	 strategy.show();
	 }
	 
	// application entry point
	 public static void main(String[] args) {
		 workingDirectory=System.getProperty("user.dir");
		 ConwaysLife w = new ConwaysLife();
	 }
	 
}
