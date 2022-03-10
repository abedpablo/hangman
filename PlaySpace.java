// Name: Abed Pablo
// Date: June 12 2021
// Description: Hangman Game

import java.util.Scanner;
import java.util.ArrayList;

public class PlaySpace {
   public static void main(String[] args) {
      while(true){
         //initialize
         System.out.println("Welcome to Hangman");
         Hangman game = new Hangman();
         game.drawTree();
         // get the difficulty and get the game phrase
         Scanner keyboard = new Scanner(System.in);
         System.out.print("Choose your difficulty: (1-Easy,2-Medium,3-Hard) ");
         int gameDifficulty = keyboard.nextInt();
         game.setPhrase(gameDifficulty);
         System.out.println(game.getHiddenPhrase());
         System.out.println("");
         // get the character guess from the user and check if it is in the phrase
         // else count up and draw the hanging man and show the guessed letters
         // in the assessGuess method
         while(game.getGuessCount() < 6){
            System.out.print("What letter would you like to guess? ");
            char letterGuess = keyboard.next().charAt(0);
            String hiddenPhrase = game.assessGuess(letterGuess);
            ArrayList<Character> guessedList = game.getGuessList();
   
            System.out.println("Guesses left: " + (6-game.getGuessCount()));
            if(guessedList.size() > 0){
               System.out.print("Guessed Letters: ");
               for(int i = 0; i<guessedList.size(); i++){
                  if(i==guessedList.size()-1){
                     System.out.println(guessedList.get(i));
                  }else{
                     System.out.print(guessedList.get(i) + ", ");
                  }
               }
            }
            // Show the working hidenPhrase
            System.out.println(hiddenPhrase);
            System.out.println("");
            // When the game is over, check what the output should be based on win or lose
            if (game.getGuessCount() == 6){
               System.out.println("Game over");
               break;
            }
            boolean solved = game.checkPhrase(hiddenPhrase);
            if (solved){
               System.out.println("Congratulations! You win.");
               break;
            }
         }
         System.out.println("");
         System.out.print("Would you like to play again? Enter any key or Enter 0 to quit ");
         char playAgain = keyboard.next().charAt(0);
         if(playAgain == '0'){
            System.out.println(" ");
            System.out.println("Bye.");
            break;
         }
      }
   }
}