import java.util.*;
import java.io.*;
public class wordleMain {

    //COLOR LIST **IGNORE THIS**
    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
//    public static final String ANSI_CYAN = "\u001B[36m";
//    public static final String ANSI_WHITE = "\u001B[37m";


    //returns a random word from the word list.
    public static String randomWord() throws FileNotFoundException {
        Scanner readWords = new Scanner(new File("src/wordList.dat"));
        double rng = Math.random()*2309;
        for(int i = 0; i < rng-1; i++){
            readWords.next();
        }
        return readWords.next();
    }
    //prints the instructions at the beginning.
    public static void instructions(){
        System.out.println("If your letter is white, that letter does not exist in the final word.");
        System.out.println();
        System.out.println("If your letter is yellow, that letter exists in the word but is in the wrong spot.");
        System.out.println();
        System.out.println("If your letter is blue, that letter exists in the word and it is in the right spot.");
        System.out.println();
        System.out.println("You have 6 guesses to guess the correct final word.");
        System.out.println();
        System.out.println(ANSI_PURPLE + "VVVVVV Enter a word under here! VVVVVV" + ANSI_RESET);
    }
    //inputs a guess from console and makes sure it's valid.
    public static String guess()throws Exception{
        Scanner input = new Scanner(System.in);
        String retGuess = input.next();
        Scanner readList = new Scanner(new File("src/allowedGuesses.dat"));
        while (readList.hasNext()){
            if(readList.next().equals(retGuess)){
                return retGuess;
            }
        }
        System.out.println("Not a valid word!");
        if(retGuess.length() != 5) System.out.println("Please make sure your guess is " + ANSI_RED + 5  + ANSI_RESET + " letters.");
        return guess();
    }
    //compares the guessed word to the final word ONLY IF the word you guessed was incorrect.
    public static String[] compare(String finWord, String guess){
        String[] wordResult = guess.split("");
        for(int i = 0; i < finWord.length(); i++){
            String guessLetter = guess.substring(i, i+1);
            for(int x = 0; x < finWord.length(); x++){
                String finalLetter = finWord.substring(x, x+1);
                if (finalLetter.equals(guessLetter)){
                    if(x != i){
                        wordResult[i] = ANSI_YELLOW + wordResult[i] + ANSI_RESET;
                    } else {
                        wordResult[i] = ANSI_BLUE + wordResult[i] + ANSI_RESET;
                    }
                }
            }
        }
        return wordResult;
    }
    public static void main(String[] args) throws Exception {

        //initial setup phase
        boolean correct = false;
        int remaining = 6;
        instructions();
        String finWord = randomWord();

        //computing
        while(!correct && remaining > 0){
            String guess = guess();
            if (guess.equals("")){
                guess();
            }
            if (guess.equals(finWord.toLowerCase())){
                System.out.println("Correct! the word was " + finWord.toLowerCase());
                correct = true;
            } else {
                System.out.println(Arrays.toString(compare(finWord.toLowerCase(), guess)));
            }
            remaining--;
            if(!correct) {
                if (remaining == 1) {
                    System.out.println(remaining + " remaining guess.");
                } else if (remaining == 0){
                    System.out.print("");
                } else {
                    System.out.println(remaining + " remaining guesses");
                }
            }
            if (remaining == 0){
                System.out.println("You ran out of guesses!");
                System.out.println("The word was: " + finWord.toLowerCase());
            }
        }
    }
}