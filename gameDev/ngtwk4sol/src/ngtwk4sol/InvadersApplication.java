package ngtwk4sol;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.awt.image.*;

public class InvadersApplication extends JFrame implements Runnable, KeyListener{
	//member variables
		private static String workingDirectory;
		private static boolean isInitialised=false;
		private static final Dimension WINDOWSIZE= new Dimension(800,800);
		private BufferStrategy strat;
		private Graphics offScreenGraphics;
		private static final int NUMALIENS=30;;
		private Alien[] AliensArray = new Alien[NUMALIENS];
		private Spaceship playerShip;
		//new stuff needed
		private Image bulletImage;
		private ArrayList bulletsList = new ArrayList();
		private int enemyWave = 1;
		private int score=0;
		private int highscore =0;
		private boolean gameInProgress=false;
		
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
			icon = new ImageIcon(workingDirectory + "\\bullet.png");
			bulletImage=icon.getImage();
			icon = new ImageIcon(workingDirectory + "\\alien_ship_2.png");
			Image alienImage2=icon.getImage();
			
			//create and initialise some aliens
			for (int i=0; i<NUMALIENS; i++) {
				AliensArray[i]=new Alien(alienImage, alienImage2);
			}
			
			//create and initialise spaceship
			icon= new ImageIcon(workingDirectory+"\\player_ship.png");
			Image shipImage=icon.getImage();
			playerShip = new Spaceship(shipImage, bulletImage);
			
			
			//tell all the sprites the wondow width
			Sprite2D.setWinWidth(WINDOWSIZE.width);
			
			//create and start thread
			Thread t = new Thread(this);
			t.start();
			
			//send key listener events
			addKeyListener(this);
			
			//initialise double buffering
			createBufferStrategy(2);
			strat=getBufferStrategy();
			offScreenGraphics=strat.getDrawGraphics();
			
			//start initialisation
			isInitialised=true;
		}
		//Thread entry point
		public void run() {
			while(true) {
				//sleep for 1/50 sec
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {}
				
				//only do this if the game is in progress
				if(gameInProgress) {
					//animate game objects
					boolean reverseNeeded=false;
					boolean anyAliensAlive=false;
					
					//if any one of the aliens hit the border, then a reversal is needed 
					for(int i=0; i<NUMALIENS; i++) {
						if(AliensArray[i].isAlive) {
							anyAliensAlive=true;
						
							if(AliensArray[i].move()) 
								reverseNeeded=true;
						
							//check if any alien collides with ship then its game over
							if(isCollision(Spaceship.x,AliensArray[i].x,Spaceship.y,AliensArray[i].y
									,54,50,32,32)) 
								gameInProgress=false;
						}
					}
					if(reverseNeeded) {
						for (int i=0; i<NUMALIENS; i++) {
							if(AliensArray[i].isAlive) {
								AliensArray[i].reverseDirection();
								//if it passes off bottom of the screen, game over
								if(AliensArray[i].y>WINDOWSIZE.height-20) {
									gameInProgress=false;
								}
							}
						}
					}
					if(!anyAliensAlive) {
						enemyWave++;
						startNewWave();
					}
					
					Spaceship.move();
					
					Iterator iterator = bulletsList.iterator();
					while(iterator.hasNext()) {
						PlayerBullet b = (PlayerBullet) iterator.next();
						if(b.move()) {
							/*
							 * true was returned bu the move method if the bullet needs destroying due to
							 * going off screen
							 * Iterator.remove is a safe way to remove from an arrayList while iterating
							 * through it
							 */
							iterator.remove();
						}
						else {
							//check if bullet collided with any aliens
							double x2=b.x, y2=b.y;
							double w1=50,h1=32;
							double w2=6, h2=16;
							for(int i=0; i<NUMALIENS; i++) {
								double x1= AliensArray[i].x, y1 = AliensArray[i].y;
								if(isCollision(x1,x2,y1,y2,w1,w2,h1,h2)) {
									//destroy the bullet and the alien
									AliensArray[i].isAlive=false;
									iterator.remove(); //safely remove bullet
									score+=10;
									if(score>highscore) {
										highscore=score;
									}
									break; //no need to check checking aliens so break out of for loop
								}
							}
						}
					}
				}
				//force repaint
				this.repaint();
			}
		}
		public void startNewWave() {
			//reposition alien and playership
			for(int i=0; i<NUMALIENS; i++) {
				double xx= (i%5)*80+70;
				double yy = (i/5)*40+50;
				AliensArray[i].setPosition(xx, yy);
				AliensArray[i].setXSpeed(1+enemyWave);
				AliensArray[i].isAlive=true;
				AliensArray[i].framesDrawn=0;
			}
			Spaceship.setPosition(300,530);
		}
		//Three Keyboard Event-Handler functions
		public void keyPressed(KeyEvent e) { //move left or right
			if (e.getKeyCode()==KeyEvent.VK_LEFT) playerShip.setXSpeed(-4);
			else if(e.getKeyCode()==KeyEvent.VK_RIGHT) playerShip.setXSpeed(4);
			else if(e.getKeyCode()==KeyEvent.VK_SPACE)
				bulletsList.add(Spaceship.shootBullet());
			else {
				startNewGame();
			}
		}
		
	
		public void keyReleased(KeyEvent e) {
			//make sure we only account for arrow keys 
			if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
				playerShip.setXSpeed(0);
		}
		public void keyTyped(KeyEvent e) {} //not needed here
		
		public void startNewGame() {
			enemyWave=1;
			score=0;
			gameInProgress=true;
			startNewWave();
			
		}
		
		//applications paaint method
		public void paint(Graphics g) {
			if(!isInitialised) return;
			
			//draw to offscreen buffer
			g= offScreenGraphics;
			
			//clear canvas with big rectangle
			g.setColor(Color.black);
			g.fillRect(0, 0, WINDOWSIZE.width, WINDOWSIZE.height);
			
			if(gameInProgress) {
				//draw game objects
				for (int i=0; i<NUMALIENS; i++) {
					AliensArray[i].paint(g);
				}
				playerShip.paint(g);
			
				//paint bullets
				Iterator i = bulletsList.iterator();
				while(i.hasNext()) {
					PlayerBullet b = (PlayerBullet) i.next();
					b.paint(g);
				}
			
				//score
				g.setColor(Color.white);
				writeString(g,WINDOWSIZE.width/2,60,30,"Score: "+score+"\tBest: "+highscore);
			}
			else { //game is nto in progress so draw menu
				g.setColor(Color.white);
				writeString(g,WINDOWSIZE.width/2,200,60,"GAME OVER");
				writeString(g,WINDOWSIZE.width/2,300,30,"press any key to play");
				writeString(g,WINDOWSIZE.width/2,350,25,"[Arrow keys to move, space to fire");
			}
			
			//flip the buffers so offscreen<-->onscreen
			strat.show();
		}
		private boolean isCollision(double x1,double x2,double y1, double y2, double w1,
				double w2, double h1, double h2) {
			if(
					((x1<x2 && x1+w1>x2) ||(x2<x1 && x2+w2>x1))
				&&
				((y1<y2 && y1+h1>y2) || (y2<y1 && y2+h2>y1))
				)
				return true;
			else 
				return false;
		}
		
		//helper method for drawing string centered at specified position
		private void writeString(Graphics g, int x, int y, int fontSize, String message) {
			Font f = new Font("Times",Font.PLAIN, fontSize);
			g.setFont(f);
			FontMetrics fm = getFontMetrics(f);
			int width = fm.stringWidth(message);
			g.drawString(message, x-width/2, y);
		}
		
		//application entry point
		public static void main (String[] args) {
			workingDirectory=System.getProperty("user.dir");
			InvadersApplication w = new InvadersApplication();
		}

}
