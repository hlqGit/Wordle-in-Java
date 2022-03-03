import java.util.*;
import java.io.*;
import java.util.Arrays;
public class wordleMain {
    //returns a random word from the word list.
    public static String randomWord() throws FileNotFoundException {
        Scanner readWords = new Scanner(new File("src/wordList.dat"));
        double rng = Math.random()*200;
        for(int i = 0; i < rng-1; i++){
            readWords.next();
        }
        return readWords.next();
    }
    public static void instructions(){ //prints the instructions at the beginning.
        System.out.println("If your letter is white, that letter does not exist in the final word.");
        System.out.println();
        System.out.println("If your letter is yellow, that letter exists in the word but is in the wrong spot.");
        System.out.println();
        System.out.println("If your letter is green, that letter exists in the word and it is in the right spot.");
        System.out.println();
        System.out.println("You have 6 guesses to guess the correct final word.");
        System.out.println();
        System.out.println("Good luck!");
    }
    public static boolean doubleLetter(String finWord, char letterCheck){
        int appearances = 0;
        return finWord.indexOf(letterCheck) != finWord.lastIndexOf(letterCheck);
    }
    public static int[] compare(String finWord, String guess){
        int[] colorSequence = new int[5];
        String confirmCorrect = null;
        for(int i = 0; i < finWord.length(); i++){
            String guessLetter = guess.substring(i, i+1);

            for(int x = 0; x < finWord.length(); x++){
                String finalLetter = finWord.substring(x, x+1);
                if (finalLetter.equals(guessLetter)){
                    if(x != i){
                        colorSequence[i] = 1;
                    } else {
                        colorSequence[i] = 2;
                        confirmCorrect = finalLetter;
                        break;
                    }
                }
            }
        }
        return colorSequence;
    }
    public static void main(String[] args) throws FileNotFoundException {

        //initial setup phase
        boolean correct = false;
        int remaining = 6;
        Scanner input = new Scanner(System.in);
        instructions();
        String finWord = randomWord();
        System.out.println(finWord.toLowerCase());

        //computing
        while(!correct && remaining > 0){
            String guess = input.next().toLowerCase();
            if (guess.equals(finWord.toLowerCase())){
                System.out.println("Correct! the word was " + finWord);
                correct = true;
            } else {
                System.out.println(Arrays.toString(compare(finWord.toLowerCase(), guess)));
            }
            remaining--;
            if (remaining == 1){
                System.out.println(remaining + " remaining guess.");
            } else {
                System.out.println(remaining + " remaining guesses.");
            }

        }
    }
}
