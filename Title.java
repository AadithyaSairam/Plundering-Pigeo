/*
	Aadithya S
	ICS4U1 - 07
	Mr.Greco
	YRDSB SVS
	June 14th 2021

	Program Description:
	This Title class extends from entity for the title image
*/
public class Title extends Background{

    public void init() {
        // Start entity's position at location (0, 0).
        this.positionX = 00;
        this.positionY = 00;
        // Call entity's method that loads its image.
        this.loadImage("src/resources/title1.png", 1000, 1000);
    }
    
}
