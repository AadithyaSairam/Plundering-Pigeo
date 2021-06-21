/*
	Aadithya S
	ICS4U1 - 07
	Mr.Greco
	YRDSB SVS
	June 14th 2021

	Program Description:
	This obstacle class extends from entity for the bottom pipe object
*/
public class Obstacle extends Entity {
    /////////////
    // FUNCTIONS//
    /////////////

    //rand for height
    public static int rand;

    //speed of the pipes
    public static int speed = -5;


    // Initializes/resets the entity.
    public void init() {

        //rand is the random generated height
        rand = (int) Math.floor(Math.random() * (600 - 200 + 1) + 200);

        // Start entity's position at location (1000, 1000 - rand).
        this.positionX = 1000;
        this.positionY = 875 - rand;

        //movement direction is set to the speed
        this.directionX = speed;

        //takes rand to adjust size
        // Call entity's method that loads its image.
        this.loadImage("src/resources/pipe.png", 100, rand);
        // System.out.print(rand);
    }


    //moves the pipe as the gaem progresses 
    public void action() {
        this.positionX += this.directionX;

        //adds score and replaces the pipe when it leaves the screen
        if (positionX == -100) {
            score++;
            //sound("jump.wav");
            init();

        }
    }

    //no keyrelease for this object
    public void keyRelease(int key) {
    }

}