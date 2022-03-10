// Name: Abed Pablo
// Date: June 12 2021
// Description: Hangman

import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Hangman {
   // Declare
   String gamePhrase; 
   String hiddenPhrase;
   int guessCount=0;
   ArrayList<Character> guessList = new ArrayList<Character>();
   
   // Read file with phrases
   public String[] readFile(String filename){
      File file = new File(filename);
      List<String> phraseList = new ArrayList<String>();
      try {
         Scanner scan = new Scanner(file);
         while(scan.hasNext()){
            phraseList.add(scan.nextLine());
         }
         scan.close();
      }
      catch (FileNotFoundException ex) {
         System.out.println("File: " + filename + " not found.");      
      }
      String [] phraseArr = phraseList.toArray(new String[0]);
      return phraseArr;
   }
   // Replace characters with hyphens
   public void hidePhrase(){
      String phrase = gamePhrase; 
      String hidden = ""; 
      for(int i = 0; i<phrase.length(); i++){
         char c = phrase.charAt(i);
         if (Character.isLetter(c)){
            hidden += "-";
         } else if(c == ' ') {
            hidden += " ";
         } else{
            hidden += Character.toString(c);
         }
      }
      hiddenPhrase = hidden;

   }
   // Get the phrase based on difficulty asked for by user
   public void setPhrase(int difficulty){

      String[] arrayOfPhrases;
      switch (difficulty) {
         case 1:
            arrayOfPhrases = readFile("easyGamePhrases.txt");
            break;
         case 2:
            arrayOfPhrases = readFile("mediumGamePhrases.txt");
            break;
         case 3:
            arrayOfPhrases = readFile("hardGamePhrases.txt");
            break;
         default:
            arrayOfPhrases = null;
            System.out.println("Incorrect input placed");
            System.exit(0);
      }
      int randomIndex = new Random().nextInt(arrayOfPhrases.length);
      String phrase = arrayOfPhrases[randomIndex];
      gamePhrase = phrase.toLowerCase();
      hidePhrase();
   }
   // tracking the amount of guesses the user has placed
   public void setGuessCount(int count){
      guessCount = count;
   }
   public int getGuessCount(){
      return guessCount;
   }
   // Return the hidden phrase
   public String getHiddenPhrase(){  
      return hiddenPhrase;
   }
   // Track the guesses made by the player
   public void addToGuessList(char guessLetter){
      guessList.add(guessLetter);
   }
   public ArrayList<Character> getGuessList(){
      return guessList;
   }
   // check if the working phrase has been solved
   public boolean checkPhrase(String phrase){
      if (phrase.equals(gamePhrase)){
         return true;
      } else {
         return false;
      }
   }
   // method to draw hanging man and tree
   public void drawTree(){
      int count = guessCount;
      String head = "         O";
      String torso = "        /|\\";
      String legs = "        /\\";
      if (count < 1){
         head = "";
         torso = "";
         legs = "";
      }
      if(count == 1){
         torso = "";
         legs = "";
      }
      if (count >=2 && count<=4){
         int toCut = -4;
         count = toCut + count;
         torso = torso.substring(0, torso.length() + count);
         legs = "";
      }
      if (count >=5 && count<=6){
         int remove = -6;
         count = remove + count;
         legs = legs.substring(0, legs.length() + count);
      }
      System.out.println("	  ___________");
      System.out.println("     |	      |");
      System.out.println("     |" + head);
      System.out.println("     |" + torso);
      System.out.println("     |" + legs);
      System.out.println("     |");
      System.out.println("     |");
      System.out.println("     |");
      System.out.println("------------");
   }
   // Take the guessed character and place it into the hyphen phrase
   // otherwise increase the guess count by one and add the letter 
   // to the guessed array list
   public String assessGuess(char guess){
      ArrayList<Integer> indexList = new ArrayList<Integer>();
      String answerString = gamePhrase;
      for(int i = 0; i<answerString.length(); i++){
         if(answerString.charAt(i) == guess){
            indexList.add(i);
         }
      }
      if(indexList.size() == 0){
         guessCount += 1;
         addToGuessList(guess);
         drawTree();
      } else {
         char[] workingArray = hiddenPhrase.toCharArray();
         for(int i = 0; i<indexList.size(); i++){
            int idx = indexList.get(i);
            workingArray[idx] = guess;
         }
      hiddenPhrase = String.valueOf(workingArray);
      }
      return hiddenPhrase;
   }
}