/*
	Aadithya S
	ICS4U1 - 07
	Mr.Greco
	YRDSB SVS
	June 14th 2021

	Program Description:
	This OppositeSpike class extends from entity for the top spike object
*/
public class OppositeSpike extends Spike {
    public void init() {
        // Start entity's position at location (0, -40).
        this.positionX = 0;
        this.positionY = -40;

        //set the animation of the spike to move at this speed
        this.directionX = -5;

        // Call entity's method that loads its image.
        this.loadImage("src/resources/Spike(1).png", 1100, 100);
    }
}
