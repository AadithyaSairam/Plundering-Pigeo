/*
	Aadithya S
	
	Program Description:
	This is the game class for my 2D game, this is responsible for all the operations in the game
	This class also consists of the engine used to recieve inputs and display the game. Engine includes 
	some functions such as mouse dragging which are no used in this game
*/

//import libraries
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.SwingUtilities;

//Class is an extension of Jpanel and also implements ActionListener for inputs
public class Game extends JPanel implements ActionListener {
	//Entity objects for obstacles and players
	static Entity[] entities;

	//background objects, holds all backgrounds
	static Background[] backgroundIMG;

	//All buttons used
	JButton restart = new JButton("Play Again");
	JButton start = new JButton("Start");
	JButton exit = new JButton("Exit");
	JButton highScore = new JButton("Add Score");
	JButton leader = new JButton("Leaderboard");
	JButton back = new JButton("Back");
	JButton help = new JButton("How to Play");

	//Used for writing to a file
	FileWriter scoreFile;

	//game represents the screen of the game is active
	static int game = 1;

	//holds the score from text file in a int array
	static int[] scoreArray;

	// Initializes the game.
	public void init() {
		//sets up buttons
		setupListeners();

		//sets the amount of backgrounds
		backgroundIMG = new Background[3];

		//sets the amount of entities
		entities = new Entity[5];

		//the player
		entities[0] = new Player();

		//both ends of the pipe
		entities[1] = new Obstacle();
		entities[2] = new OppositeObstacle();

		//top and bottom spike
		entities[3] = new Spike();
		entities[4] = new OppositeSpike();

		//3 backgrounds
		backgroundIMG[0] = new Background();
		backgroundIMG[1] = new HowToPlay();
		backgroundIMG[2] = new Title();

		//initialize all the objects
		entities[0].init();
		entities[1].init();
		entities[2].init();
		entities[3].init();
		entities[4].init();
		backgroundIMG[0].init();
		backgroundIMG[1].init();
		backgroundIMG[2].init();
	}

	// Updates all game objects.
	public void step(ActionEvent e) {

		// start button
		if (e.getSource() == start) {
			//call Action.start() which holds the commands after the button press
			Action.start();
			//remove and repaint
			remove(back);
			remove(restart);
			remove(start);
			remove(exit);
			remove(highScore);
			remove(leader);
			remove(help);
			repaint();
		}
		//Play again button
		if (e.getSource() == restart) {
			//call Action.restart() which holds the commands after the button press
			Action.restart();
			// remove and repaint
			remove(back);
			remove(restart);
			remove(start);
			remove(exit);
			remove(highScore);
			remove(leader);
			remove(help);
			repaint();
		}
		//exit button
		if (e.getSource() == exit) {
			//closes the program
			System.exit(0);
		}
		//add score button
		if (e.getSource() == highScore) {
			//call Action.adScore() which holds the commands after the button press
			Action.addScore();
			//remove and repaint
			remove(highScore);
			repaint();
		}
		//leaderboard button
		if (e.getSource() == leader) {
			//call Action.leader() which holds the commands after the button press
			Action.leader();
			//remove and repaint
			remove(back);
			remove(restart);
			remove(start);
			remove(exit);
			remove(highScore);
			remove(leader);
			remove(help);
			repaint();
		}
		//back button
		if (e.getSource() == back) {
			//call Action.back() which holds the commands after the button press
			Action.back();
			//remove and repaint
			remove(back);
			remove(restart);
			remove(start);
			remove(exit);
			remove(highScore);
			remove(leader);
			remove(help);
			repaint();
		}
		//help button
		if (e.getSource() == help) {
			//call Action.help() which holds the commands after the button press
			Action.help();
			//remove and repaint
			remove(back);
			remove(restart);
			remove(start);
			remove(exit);
			remove(highScore);
			remove(leader);
			remove(help);
			repaint();
		}

		//when game screen is 2 (the actual game) call the entity's action
		if (game == 2) {
			backgroundIMG[0].action();
			entities[0].action();
			entities[1].action();
			entities[2].action();
			entities[3].action();
			entities[4].action();
			//check if the game is over
			endGame();
		} 
		
	}

	// Draws all game objects.
	public void draw(Graphics2D g2d) {
		
		//draw all objects and visuals for the actual active game
		if (game == 2) {
			backgroundIMG[0].draw(g2d, this);
			entities[0].draw(g2d, this);
			entities[1].draw(g2d, this);
			entities[2].draw(g2d, this);
			entities[3].draw(g2d, this);
			entities[4].draw(g2d, this);
			Font font = new Font("Helvetica Neue", Font.BOLD, 24);
			g2d.setFont(font);
			//show current score
			g2d.drawString("Score: " + (Entity.getScore()), 840, 160);

		//drwa objects, visuals and text for title screen
		} else if (game == 1) {
			//background
			backgroundIMG[0].draw(g2d, this);
			//set font
			Font font = new Font("Tahoma", Font.BOLD, 96);
			//backgroundIMG[2].draw(g2d, this);

			//write the title
			g2d.setColor(Color.BLACK);
			g2d.setFont(font);
			g2d.drawString("Plundering", 235, 300);
			g2d.drawString("Pigeon", 335, 400);

			//new font for created by tag
			font = new Font("Tahoma", Font.BOLD, 12);
			g2d.setColor(Color.BLACK);
			g2d.setFont(font);
			g2d.drawString("Made By: Aadithya Sairam", 800, 50);

			//place start button
			start.setBounds(150, 450, 100, 50);
			start.setBackground(new Color(128, 128, 128));
			start.setFont(new Font("Tahoma", Font.PLAIN, 15));
			start.setBorder(null);
			add(start);

			//place exit button
			exit.setBounds(750, 450, 100, 50);
			exit.setBackground(new Color(128, 128, 128));
			exit.setFont(new Font("Tahoma", Font.PLAIN, 15));
			exit.setBorder(null);
			add(exit);

			//place leaderboard button
			leader.setBounds(350, 450, 100, 50);
			leader.setBackground(new Color(128, 128, 128));
			leader.setFont(new Font("Tahoma", Font.PLAIN, 15));
			leader.setBorder(null);
			add(leader);

			//place help button
			help.setBounds(550, 450, 100, 50);
			help.setBackground(new Color(128, 128, 128));
			help.setFont(new Font("Tahoma", Font.PLAIN, 15));
			help.setBorder(null);
			add(help);

		//draw objects, vsuals and text for game over page
		} else if (game == 3) {
			//background
			backgroundIMG[0].draw(g2d, this);

			//font settings
			g2d.setColor(Color.BLACK);
			Font font = new Font("Tahoma", Font.BOLD, 96);

			//game over and score text
			g2d.setFont(font);
			g2d.drawString("GAME OVER", 190, 400);
			g2d.drawString("Score: " + (Entity.getScore()), 210, 500);

			//place restart button
			restart.setBounds(450, 550, 100, 50);
			restart.setBackground(new Color(128, 128, 128));
			restart.setFont(new Font("Tahoma", Font.PLAIN, 15));
			restart.setBorder(null);
			add(restart);

			//place addscore button
			highScore.setBounds(250, 550, 100, 50);
			highScore.setBackground(new Color(128, 128, 128));
			highScore.setFont(new Font("Tahoma", Font.PLAIN, 15));
			highScore.setBorder(null);
			add(highScore);

		//draw objects, visuals and text for game over after adding score
		} else if (game == 5) {
			//background
			backgroundIMG[0].draw(g2d, this);

			//font settings
			g2d.setColor(Color.BLACK);
			Font font = new Font("Tahoma", Font.BOLD, 96);

			//draw game over and score
			g2d.setFont(font);
			g2d.drawString("GAME OVER", 190, 400);
			g2d.drawString("Score: " + (Entity.getScore()), 210, 500);

			//place play again button
			restart.setBounds(450, 550, 100, 50);
			restart.setBackground(new Color(128, 128, 128));
			restart.setFont(new Font("Tahoma", Font.PLAIN, 15));
			restart.setBorder(null);
			add(restart);

		//draw the leaderboard page
		} else if (game == 4) {
			//background
			backgroundIMG[0].draw(g2d, this);

			//font settings
			g2d.setColor(Color.BLACK);
			Font font = new Font("Tahoma", Font.BOLD, 56);
			g2d.setFont(font);

			//try catch for readign score text file
			try (FileReader file = new FileReader("Scoring.txt")) {
				Scanner src = new Scanner(file);

				//use a delimeter to get individual values
				src.useDelimiter(",");
				
				//draw the leadrboard title
				g2d.drawString("Top 10 Leaderboard", 250, 100);

				//new font settings
				font = new Font("Tahoma", Font.BOLD, 24);
				g2d.setFont(font);

				//for loop to display top 10 scores or all score if less than 10
				for (int i = 0; i < 10 && i < Action.length; i++) {

					//if statement for correct sufixes
					String sufix = "th";
					if (i + 1 == 1) {
						sufix = "st";
					} else if (i + 1 == 2) {
						sufix = "nd";
					} else if (i + 1 == 3) {
						sufix = "rd";
					}

					//write out the score below each other
					g2d.drawString(((i + 1) + sufix + ": " + src.next()), 450, 200 + 50 * i);
				}
				//close scanner 
				src.close();
			
				//catch IOException
			} catch (IOException e1) {
				System.err.println("Error occurred");
				e1.printStackTrace();
			}

			//place back button
			back.setBounds(700, 200, 100, 50);
			back.setBackground(new Color(128, 128, 128));
			back.setFont(new Font("Tahoma", Font.PLAIN, 15));
			back.setBorder(null);
			add(back);
		}

		//draw objects for the how to play screen
		else if (game == 6) {
			//place background
			backgroundIMG[0].draw(g2d, this);
			//place how to play background/ infopanel
			backgroundIMG[1].draw(g2d, this);

			//place back button
			back.setBounds(750, 500, 100, 50);
			back.setBackground(new Color(128, 128, 128));
			back.setFont(new Font("Tahoma", Font.PLAIN, 15));
			back.setBorder(null);
			add(back);
			repaint();
		}
		
	}

	//Function to setup action listeners for each Jbutton
	public void setupListeners() {

		restart.addActionListener(this);
		start.addActionListener(this);
		exit.addActionListener(this);
		highScore.addActionListener(this);
		leader.addActionListener(this);
		back.addActionListener(this);
		help.addActionListener(this);
	}

	//Function that checks the intersection of entities and determines endgame
	public void endGame() {
		if (entities[0].getRect().intersects(entities[1].getRect())
				|| entities[0].getRect().intersects(entities[2].getRect())
				|| entities[0].getRect().intersects(entities[3].getRect())
				|| entities[0].getRect().intersects(entities[4].getRect())) {
			//return the screen to the game over page
			game = 3;
		} else {
			//continue game
			game = 2;
		}
	}

	//adds score 
	// public void scoreAdd(int score) throws IOException {
	// 	scoreFile = new FileWriter("Scoring.txt", true);
	// 	scoreFile.write(score);

	// }

	///////////////////
	// INPUT FUNCTIONS//
	///////////////////
	// Automatically activates when a keyboard key is pressed.
	public void keyPress(int key) {
		// Sends key-press input to player.

		// Prints key pressed to console log.
		// System.out.println("Key Pressed: " + key);
	}

	// Automatically activates when a keyboard key is released.
	public void keyRelease(int key) {
		// Sends key-release input to player.
		entities[0].keyRelease(key);

		// Prints key released to console log.
		// System.out.println("Key Released: " + key);
	}

	// Activates when mouse button is pressed and then released.
	public void mouseClick(int x, int y, int button) {
		// Prints mouse coordinates to console log.
		// System.out.println("Mouse button " + button + " clicked: (" + x + ", " + y +
		// ")");
	}

	// Activates when mouse button is pressed.
	public void mousePress(int x, int y, int button) {
		// Prints mouse coordinates to console log.
		// System.out.println("Mouse button " + button + " pressed: (" + x + ", " + y +
		// ")");
	}

	// Activates when mouse button is released.
	public void mouseRelease(int x, int y, int button) {
		// Prints mouse coordinates to console log.
		// System.out.println("Mouse button " + button + " released: (" + x + ", " + y +
		// ")");
	}

	// Activates when mouse cursor enters window.
	public void mouseEnter(int x, int y) {
		// Prints mouse coordinates to console log.
		// System.out.println("Mouse Entered: (" + x + ", " + y + ")");
	}

	// Activates when mouse cursor exits window.
	public void mouseExit(int x, int y) {
		// Prints mouse coordinates to console log.
		// System.out.println("Mouse Exited: (" + x + ", " + y + ")");
	}

	// Activates when mouse moves within the window.
	public void mouseMovement(int x, int y) {
		// Prints mouse coordinates to console log.
		// System.out.println("Mouse Moved: (" + x + ", " + y + ")");
	}

	// Activates when mouse is moved while button is pressed within the window.
	public void mouseDragging(int x, int y, int button) {
		// Prints mouse coordinates to console log.
		// System.out.println("Mouse button " + button + " dragged: (" + x + ", " + y +
		// ")");
	}

	// Activates when mouse wheel has moved.
	public void mouseWheel(int direction) {
		// Prints mouse wheel amount to console log.
		// System.out.println("Mouse wheel direction: " + direction);
	}

	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// ENGINE////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	// Program animation speed interval.
	private final int DELAY = 15;

	// Updates program based on speed delay interval.
	private Timer timer;

	// Constructs/initializes the game.
	public Game() {
		// Sets up keyboard input, using nested private GameKeyboardAdapter class.
		addKeyListener(new GameKeyboardAdapter());
		addMouseListener(new GameMouseListener());
		addMouseMotionListener(new GameMouseMotionListener());
		addMouseWheelListener(new GameMouseWheelListener());

		// Initializes the game.
		init();

		// Allows keyboard input to work.
		setFocusable(true);

		// For every DELAY amount in milliseconds, the timer will call the
		// actionPerformed() method,
		// which updates the movement of objects in the game. Think of it as the
		// program's frame-rate.
		this.timer = new Timer(DELAY, this);
		this.timer.start();
	}

	// All game graphics are drawn inside the paintComponent() method.
	@Override
	public void paintComponent(Graphics g) {
		// Draws JPanel window
		super.paintComponent(g);

		// The Graphics2D class extends the Graphics class.
		// It provides more sophisticated control over geometry,
		// coordinate transformations, colour management, and text layout.
		Graphics2D g2d = (Graphics2D) g;

		// Draws all game components.
		draw(g2d);

		// Toolkit.getDefaultToolkit().sync() synchronises the painting on systems that
		// buffer graphics events.
		// Without this line, the animation might not be smooth on Linux.
		Toolkit.getDefaultToolkit().sync();
	}

	// Updates the movement of objects in the game. This method is repeatedly called
	// by the Timer object.
	// NOTE: In order to use the actionPerformed() method, the ActionListener
	// interface must be implemented
	// in the class' signature header, above.
	@Override
	public void actionPerformed(ActionEvent e) {
		// Updates all game objects.
		step(e);

		// Causes the paintComponent() drawing method to be called.
		// This way we can regularly redraw the Game, thus making the animation.
		repaint();

		
	}

	// This inner private class detects keyboard inputs by the user.
	private class GameKeyboardAdapter extends KeyAdapter {
		// Detects whenever a key (represented by variable "e") is released.
		@Override
		public void keyReleased(KeyEvent e) {
			// Gets the key value of the key just released.
			int key = e.getKeyCode();

			// Send key to game function.
			keyRelease(key);
		}

		// Detects whenever a key (represented by variable "e") is pressed down.
		@Override
		public void keyPressed(KeyEvent e) {
			// Gets the key value of the key just pressed.
			int key = e.getKeyCode();

			// Send key to game function.
			keyPress(key);
		}
	}

	// This inner private class detects mouse inputs and location by the user.
	private class GameMouseListener implements MouseListener {
		// Activates when mouse button is pressed and then released.
		@Override
		public void mouseClicked(MouseEvent e) {
			// Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			// Figures out which button on mouse was pressed.
			// 0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			// Sends mouse coordinates to game function.
			mouseClick(mouseX, mouseY, mouseButton);
		}

		// Activates when mouse button is pressed.
		@Override
		public void mousePressed(MouseEvent e) {
			// Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			// Figures out which button on mouse was pressed.
			// 0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			// Sends mouse coordinates to game function.
			mousePress(mouseX, mouseY, mouseButton);
		}

		// Activates when mouse button is released.
		@Override
		public void mouseReleased(MouseEvent e) {
			// Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			// Figures out which button on mouse was pressed.
			// 0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			// Sends mouse coordinates to game function.
			mouseRelease(mouseX, mouseY, mouseButton);
		}

		// Activates when mouse cursor enters window.
		@Override
		public void mouseEntered(MouseEvent e) {
			// Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			// Sends mouse coordinates to game function.
			mouseEnter(mouseX, mouseY);
		}

		// Activates when mouse cursor exits window.
		@Override
		public void mouseExited(MouseEvent e) {
			// Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			// Sends mouse coordinates to game function.
			mouseExit(mouseX, mouseY);
		}
	}

	// This inner private class detects mouse movement and location by the user.
	private class GameMouseMotionListener extends Frame implements MouseMotionListener {
		// Activates when mouse moves within the window.
		@Override
		public void mouseMoved(MouseEvent e) {
			// Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			// Sends mouse coordinates to game function.
			mouseMovement(mouseX, mouseY);
		}

		// Activates when mouse is moved while button is pressed within the window.
		@Override
		public void mouseDragged(MouseEvent e) {
			// Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			// Figures out which button on mouse was pressed.
			// 0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			// Sends mouse coordinates to game function.
			mouseDragging(mouseX, mouseY, mouseButton);
		}
	}

	// This inner private class detects mouse wheel movement by the user.
	private class GameMouseWheelListener extends JPanel implements MouseWheelListener {
		// Activates when mouse wheel has moved.
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// Sends mouse wheel status to game function.
			// -1 means downwards, 1 means upwards.
			mouseWheel(e.getWheelRotation());
		}
	}
}