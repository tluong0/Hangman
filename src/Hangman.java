
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Random;
import java.util.Scanner;

/*

 */
/**
 *
 * @author leahluong
 */
public class Hangman {

    int wordLength;
    int numberOfGuess;
    private String wordChoice;
    int guessNum;
    char guess;
    char[] arrayDisplay;
    char[] usedChar = new char[26];
    int countGame;
    boolean test;
    int guessRight = 0;
    int numEntered = -1;

    public Hangman() {
        this.wordLength = 0;
        this.numberOfGuess = 0;
    }

    public Hangman(int wordLength, int numberOfGuess) {
        this.wordLength = wordLength;
        this.numberOfGuess = numberOfGuess;
    }

    public void playGame() {
        
        
        System.out.print("Enter a word length (5-7): ");
        
        Scanner input = new Scanner(System.in);
        if(input.hasNextInt()){
        wordLength = input.nextInt();
        }
        else {
            System.out.println("Invalid input. Please try again.");
            playGame();
        }
        System.out.println("Number of missed guesses(1-20):");
        if(input.hasNextInt()){
        guessNum = input.nextInt();
        }
        else {
            System.out.println("Invalid input. Please try again.");
            playGame();
        }
        
        countGame = guessNum;
        getWord();
        underlineDisPlay();

        getGuessedWord();

    }

    public void getWord() {
        try {
            String fileToLoad = "Dic7L.txt";
            int maxLength = 23875;
            FileReader read;
            Random random = new Random();
            int rand;
            if (wordLength == 5) {
                fileToLoad = "Dic5L.txt";
                maxLength = 9937;
            } else if (wordLength == 6) {
                fileToLoad = "Dic6L.txt";
                maxLength = 16839;
            } else {
                maxLength = 23875;
            }

            read = new FileReader(fileToLoad);
            Scanner input = new Scanner(read);

            rand = random.nextInt(maxLength);
            //System.out.println("rand = " + rand);
            int count = 0;

            while (input.hasNextLine()) {
                count++;

                String word = input.nextLine();

                if (count == rand) {
                    wordChoice = word;
                    //System.out.println("wordChoice = " + wordChoice);
                    break;
                }

            }

        } catch (FileNotFoundException ex) {
            System.err.println("Could not open file.");
        }
    }

    public void underlineDisPlay() {

        arrayDisplay = new char[wordLength];
        for (int i = 0; i < arrayDisplay.length; i++) {
            arrayDisplay[i] = '_';
            System.out.print(arrayDisplay[i] + " ");

        }
        System.out.println("\n");

    }

    public void getGuessedWord() {
//        while(i<20) {
        while (countGame > 0) {
            System.out.print("Enter guess: ");
            Scanner input = new Scanner(System.in);

            guess = input.next().toUpperCase().charAt(0);

            numEntered++;
            //System.out.println(""+guess); 
            
            checkGuessedWord();
            usedChar[numEntered] = guess;
            System.out.println("");
            System.out.print("letters used: ");
            for (int i = 0; i <= numEntered; i++) {
                System.out.print(usedChar[i]+" ");
            }
            System.out.println("");

//            }
        }
    }

    public void checkGuessedWord() {
        test = false;
        for (int i = 0; i <= numEntered +1; i++) {
            if (usedChar[i] == guess) {
                System.out.println("You used this letter before.");
                System.out.println("Please enter different letter.");
                numEntered--;
                getGuessedWord();
            }
        }
                int j;
                //System.out.println("" +wordLength);
                for (j = 0; j < wordLength; j++) {
                    //System.out.print(wordChoice.charAt(j));
                    if (guess == wordChoice.charAt(j)) {
                        arrayDisplay[j] = guess;
                    //   System.out.print(arrayDisplay[j]);
                        guessRight++;
                        test = true;

                    }
                }
            
                checkWord();
            
        
    }

    public void checkWord() {
        if (test == true) {
            System.out.print("Word: ");
            wordDisplay();

            //System.out.println(guessRight);
            if (guessRight == wordLength) {
                loseWin();
            } else {
               // getGuessedWord();
            }

        } else {
            System.out.println("There is no " + guess + " in this word");
            System.out.print("Word: ");
            wordDisplay();
            countGame--;

            if (countGame == 0) {
                loseWin();
            } else {
                System.out.println("You have " + countGame + " guess(es) left");
              //  getGuessedWord();

            }
        }
    }

    public void wordDisplay() {
        for (int i = 0; i < arrayDisplay.length; i++) {

            System.out.print(arrayDisplay[i] + " ");
        }
    }

    public void loseWin() {
        boolean game = false;
        if (countGame == 0) {
            System.out.println(countGame);
            System.out.println("You lose.");
            System.out.println("Word is " + wordChoice);
            rePlay();
        } else {
            for (int i = 0; i < arrayDisplay.length; i++) {
                if (arrayDisplay[i] == '_') {
                    game = false;

                } else {
                    game = true;

                }

            }
            if (game == false) {
                System.out.println("You lose.");
                System.out.println("Word is " + wordChoice);
                rePlay();
            } else {
                System.out.println("Congratulations, you win!");
                rePlay();
            }
        }
    }

    public void rePlay() {
        System.out.println("Do you want to play again? (Y/N)");
        Scanner input = new Scanner(System.in);
        char choice = input.next().toUpperCase().charAt(0);
        if (choice == 'Y') {
            usedChar = new char[26];
            numEntered=-1;
            guessRight=0;
            playGame();
        } else if (choice == 'N') {
            System.out.println("Thanks for playing!");
            System.exit(0);
        } else {
            rePlay();
        }

    }
}
