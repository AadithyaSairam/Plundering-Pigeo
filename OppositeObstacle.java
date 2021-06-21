/*
	Aadithya S
	
	Program Description:
	This OppositeObstacle class extends from entity for the top pipe object
*/

public class OppositeObstacle extends Obstacle {

    //initialize/ reset entity
    public void init() {
        // Start entity's position at location (1000, 0).
        this.positionX = 1000;
        this.positionY = 0;

        //sets movement to speed
        this.directionX = speed;

        //takes rand to adjust size
        // Call entity's method that loads its image.
        this.loadImage("src/resources/pipe(1).png", 100, 675 - rand);
    }
}
