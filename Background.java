/*
	Aadithya S
	ICS4U1 - 07
	Mr.Greco
	YRDSB SVS
	June 14th 2021

	Program Description:
	This background class contains the image to be place in the background of every scene in the game
*/

//entends for the entity class
public class Background extends Entity {
    /////////////
    // FUNCTIONS//
    /////////////

    // Initializes/resets the entity.
    public void init() {
        // Start entity's position at location (0, 0).
        this.positionX = 00;
        this.positionY = 00;
        // Call entity's method that loads its image.
        this.loadImage("src/resources/bg.png", 1000, 875);
    }

    //no action need for this object
    public void action() {
    }

    //no keyrelease for this object
    public void keyRelease(int key) {
    }

}