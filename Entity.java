/*
	Aadithya S
	ICS4U1 - 07
	Mr.Greco
	YRDSB SVS
	June 14th 2021

	Program Description:
	This Entity abstract class houses the generic functions and variables used for 
    any player, object or background in the game
*/

//import libraries
import java.awt.Graphics2D;
import java.awt.Image;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.io.File;

//abstract class
abstract class Entity {
    /////////////
    // VARIABLES//
    /////////////

    //image of the entity
    protected Image image;
    
    //x and y coordinates
    protected int positionX;
    protected int positionY;

    //movement in x and y direction
    protected int directionX;
    protected int directionY;

    //height and width of image
    protected int width;
    protected int height;

    //game score
    protected static int score;

    /////////////
    // FUNCTIONS//
    /////////////

    // Loads the entity's image into the game, and sets its length and width.
    public void loadImage(String img, int w, int h) {
        // Uses the ImageIcon class to load the image file into the program.
        ImageIcon file = new ImageIcon(img);

        // Assigns the image data to its image variable reference.
        this.image = file.getImage();

        // Sets the entity's width and height to be that of the image just loaded in.
        this.width = this.image.getWidth(null);
        this.height = this.image.getHeight(null);

        // Resizes the image to width and height.
        this.width = w;
        this.height = h;
        this.image = this.image.getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT);
    }

    public void draw(Graphics2D g2d, Game game) {
        // Draws the entity's image in the program window, at its x and y location.
        g2d.drawImage(this.image, this.positionX, this.positionY, game);
    }

    /////////////////////////
    /// ABSTRACT FUNCTIONS/////
    ////////////////////////

    //Initializes/resets the entity.
    protected abstract void init();

    //action command that is continuously called and recieving inputs
    abstract void action();

    //what should be performed upon key release of a certain key
    abstract void keyRelease(int key);

    //add sound effects if time permits 
    //(Note: kind of glitchy and loud at the current state so it is comments out in the iobstacle class)
    public void sound(String filepath) {

        Clip clip;

        try {

            File soundFile = new File("src/resources/" + filepath); // Gets sounds from the 'sounds' filepath
            AudioInputStream input = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start(); // Starts audio clip

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // Getter functions.
    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public int getDirectionX() {
        return this.directionX;
    }

    public int getDirectionY() {
        return this.directionY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Image getImage() {
        return this.image;
    }

    public static void setScore(int x) {
        score = x;
    }

    public static int getScore() {
        return score / 2;
    }

    public Rectangle getRect() {
        return new Rectangle(this.positionX, this.positionY, this.width, this.height);
    }
}