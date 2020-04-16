package tutor;

import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class TypingTutor_Controller {

    TypingTutor_Controller(){
        initialize();
        type_duration = trackTypeDuration();
    }

    static  Boolean isFirstTime = true;
    String[] paragraphs = new String[15];
    String paragraph;
    public char[] characters;
    int number_of_words;
    int number_of_characters;
    int type_duration;

    //scores
    int wpm=0;
    int cps=0;



    private void initialize(){
        //load paragraphs on first initialisation
        if(isFirstTime){
            loadParagraphs();
            isFirstTime = false;
        }

        //load a random paragraph
        Random random = new Random();
        int nextParagraph = random.nextInt(15);

        this.paragraph = paragraphs[nextParagraph];
        this.characters = this.paragraph.toCharArray();
        this.number_of_words =  countWords();
        this.number_of_characters = countCharacters();
    }

    //reads file en.paragraphs and loads all the paragraphs into the paragraphs array.
    public void loadParagraphs() {
       try{
           FileInputStream fs = new FileInputStream("paragraphs/en.paragraph");
           Scanner sc = new Scanner(fs);
           int a = 0;
           while (sc.hasNextLine()){
               paragraphs[a]= sc.nextLine();

               a++;
           }
           sc.close();
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    private int countWords(){
        //returns number of words in paragraph
        String[] words = paragraph.split(" ");
        return words.length+1;
    }

    private int countCharacters(){
        //returns number of characters in paragraph
        return characters.length+1 ;
    }

    private int trackTypeDuration(){

        return 0;
    }

    public void findScores(){
        this.wpm = number_of_words / (type_duration / 60);
        this.cps = number_of_characters/type_duration;
    }

    public int getType_duration() {
        return type_duration;
    }

    public int getWpm() {
        return wpm;
    }

    public int getCps() {
        return cps;
    }
}
