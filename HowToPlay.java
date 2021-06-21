/*
	Aadithya S

	Program Description:
	This is the HowToPlay class which extends from background to display to info panel
*/
public class HowToPlay extends Background{

    public void init() {
        // Start entity's position at location (0, 0).
        this.positionX = 00;
        this.positionY = 00;
        // Call entity's method that loads its image.
        this.loadImage("src/resources/instructionLabel.png", 1000, 1000);
    }
    
}
