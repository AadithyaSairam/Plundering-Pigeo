/*
	Aadithya S
    
	Program Description:
	This action class contains functions and commands run by the buttons from the game
*/

//import libraries
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//extension of the game class
public class Action extends Game {

    //amount of score on the txt file
    static int length = 0;

    //leaderboard command
    public static void leader() {
        //set gaem screen to leaderboard page
        game = 4;
        //reset length
        length = 0;

        //try catch for file read and write
        try (FileReader file = new FileReader("Scoring.txt")) {
            Scanner src = new Scanner(file);

            //hold string value of txt file
            String test = "";

            try (FileReader file2 = new FileReader("Scoring.txt")) {
                Scanner str = new Scanner(file2);
                
                //given a copy of the file contents
                test = str.nextLine();
                str.close();
            } catch (IOException e1) {
                System.err.println("Error occurred");
                e1.printStackTrace();
            }

            //use delimeter "," to get individual values
            src.useDelimiter(",");

            //use test to get the amount of scores in the file by counting commas
            for (int i = 0; i < test.length(); i++) {
                if (test.charAt(i) == ',') {
                    //incremetn length variable
                    length++;
                }
            }

            //create an array with the now found length
            scoreArray = new int[length];

            //for loop to clear array
            for (int i = 0; i < length && src.hasNextInt(); i++) {
                scoreArray[i] = 0;
            }

            //for loop to fill the array
            for (int i = 0; i < length && src.hasNextInt(); i++) {
                scoreArray[i] = src.nextInt();
            }

            //sort the array using built in sorting function
            Arrays.sort(scoreArray);

            //try catch for writing into the file
            try (FileWriter out = new FileWriter("Scoring.txt")) {

                //rewrite sorted array on into the txt file
                for (int i = length - 1; i >= 0; i--) {
                    out.write(scoreArray[i] + ",");
                }
            
            //catch IOException
            } catch (IOException e1) {
                System.err.println("Error occurred");
                e1.printStackTrace();
            }
            //close the scanner for the source file
            src.close();

        //catch IOException
        } catch (IOException e1) {
            System.err.println("Error occurred");
            e1.printStackTrace();
        }
    }

    //restart/ play again command
    public static void restart() {
        //set game screen to home screen
        game = 1;
        
        //initialize necessary components
        backgroundIMG[0].init();
    }

    //start command
    public static void start() {
        //reset score
        Entity.setScore(0);
        
        //set gaem screen to the active game
        game = 2;

        //initialize necessary components
        backgroundIMG[0].init();
        entities[0].init();
        entities[1].init();
        entities[2].init();
    }

    //addscore commmand
    public static void addScore() {
        //set game screen to gaem over without add score button
        game = 5;

        //try catch to write to a file
        try (FileWriter out = new FileWriter("Scoring.txt", true)) {
            //append the new score
            out.write(Entity.getScore() + ",");
            
        //catch IOException
        } catch (IOException e1) {
            System.err.println("Error occurred");
            e1.printStackTrace();
        }
    }

    //back command
    public static void back() {
        //redirects to hoem page
        game = 1;
    }

    //how to play command
    public static void help(){
        //redirects to help info panel
        game = 6;
    }

}
