package tutor;

import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class TypingTutor_Controller {

    TypingTutor_Controller(){
        initialize();

        type_duration = 0;
        wpm = 0; cps = 0;
    }

    String[] paragraphs = new String[15];

    String paragraph ;
    private char[] typedParagraph;
    public char[] characters;

    float type_duration;

    //scores
    float wpm;
    float cps;



    public void initialize(){
        loadParagraphs();
       //load a random paragraph
        Random random = new Random();
        int nextParagraph = random.nextInt(15);

        this.paragraph = paragraphs[nextParagraph];
        this.characters = this.paragraph.toCharArray();

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
    public void trackTypeDuration(long duration){
        //divide milliseconds by 1000 to get value in seconds
        this.type_duration = (float) (duration / 1000);

    }

    public void findScores(String text){
        String[]words = text.split(" ");
        char[]chars = text.toCharArray();
        this.wpm = words.length / (type_duration / 60);
        this.cps = chars.length/type_duration;
    }

    public float getType_duration() {
        return type_duration;
    }

    public float getWpm() {
        return wpm;
    }

    public float getCps() {
        return cps;
    }

    public void updateTypedParagraph(String text) {
        this.typedParagraph = text.toCharArray();
    }

    public boolean typedCharactersMatchParagraph() {
        char[]chars = new char[typedParagraph.length];
        System.arraycopy(characters, 0, chars, 0, typedParagraph.length);
        return equals(chars, typedParagraph);
    }

    public boolean equals(char[]obj1, char[]obj2) {
        if(obj1.length != obj2.length){
            return false;
        }else {
            for (int i = 0; i < obj1.length; i++){
                if(obj1[i] != obj2[i]){
                    return false;
                }
            }
            return true;
        }
    }
}
