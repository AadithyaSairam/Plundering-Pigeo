/*
	Aadithya S
	ICS4U1 - 07
	Mr.Greco
	YRDSB SVS
	June 14th 2021

	Program Description:
	This spike class extends from entity for the top spike object
*/

public class Spike extends Entity {
    /////////////
    // FUNCTIONS//
    /////////////

    // Initializes/resets the entity.
    public void init() {
        // Start entity's position at location (0, 900).
        this.positionX = 0;
        this.positionY = 775;

        //set the animation of the spike to move at this speed
        this.directionX = -5;

        // Call entity's method that loads its image.
        this.loadImage("src/resources/Spike.png", 1100, 100);
    }

    //moves the spikes at the set speed and resets them to simulate the game moving forward
    public void action() {
        //move object
        this.positionX += this.directionX;

        //when object moves off screen replace it
        if (positionX == -100) {
            init();
        }
    }

    //no keyrelease for this object
    public void keyRelease(int key) {
    }

}