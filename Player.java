/*
	Aadithya S

	Program Description:
	This Player extends from entity to create the interactive player object
*/

//import library 
import java.awt.event.KeyEvent;

public class Player extends Entity {
	/////////////
	// FUNCTIONS//
	/////////////

	// Initializes/resets the entity.
	public void init() {
		// Start entity's position at location (50, 500).
		this.positionX = 50;
		this.positionY = 500;

		// Call entity's method that loads its image.
		this.loadImage("src/resources/BIRD.gif", 50, 67);
	}

	//when gaem progresses the bird will drop to simulate gravity
	public void action() {
		this.positionY += this.directionY;

		//checks that the movement down is less that 5
		//if it is less than 5 start dropping the bird
		if (this.directionY < 5) {
			this.directionY += 2;
		}
	}

	//no key press for this object
    public void keyPress(int key) {
    }

	//upon key release jump
	public void keyRelease(int key) {
		//when up arrow is released
		if (key == KeyEvent.VK_UP) {
			
			//stop the birds drop
			if (this.directionY > 0) {
				this.directionY = 0;
			}
			
			//jump up
			this.directionY = -15;

		}
	}

}