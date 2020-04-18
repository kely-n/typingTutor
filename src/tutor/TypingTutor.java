package tutor;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.*;


public class TypingTutor extends Application {
    
    TypingTutor_Controller engine = new TypingTutor_Controller();
    @FXML private Label paragraph;
    @FXML private Button submit;
    @FXML private TextArea input;
    int count = 0;int errors = 0;
    @FXML public void initialize(){

        if(Screen.getPrimary().getBounds().getWidth()>1200){
            paragraph.setStyle("-fx-font-family: Monospaced;-fx-text-alignment: center;-fx-text-fill: forestgreen;-fx-font-size: 20");

        }
        paragraph.setText("thanks for choosing to use the Typing_Tutor.\n Success in sharpening your touch typing skill!\n ________Bon Courage!________ ");

    }
    long start;
    @FXML protected void handleSubmitButtonAction() {

        if(submit.getText().equals("New Paragraph")) {
            stop_RefreshTutor();
        }
        startTutoring();

    }
    private void stop_RefreshTutor(){
        engine.initialize();
        paragraph.setText(engine.paragraph);
        input.setVisible(false);
        submit.setText("Start");
        count = 0; errors = 0;
        input.setText(null);
    }
    private void startTutoring() {
        paragraph.setText(engine.paragraph);
        input.setVisible(true);
        input.setEditable(true);
        input.requestFocus();
        start = System.currentTimeMillis();
        submit.setText("New Paragraph");

    }


    @FXML protected void handleKeyPressedAction(KeyEvent event) {
        char[]characters = engine.characters;

        if (count<=characters.length) {
            if (event.getCode() == BACK_SPACE && count > 0) {
                count--;
                engine.updateTypedParagraph(input.getText());
                changeInputTextColor();

            } else if (
                    !(event.getCode().isFunctionKey()
                            && event.getCode().isArrowKey()
                            && event.getCode().isMediaKey()
                            && event.getCode().isModifierKey()
                            && event.getCode().isNavigationKey())
            ) {
                if (
                        event.getCode().isLetterKey()
                                || event.getCode().isDigitKey()
                                || event.getCode() == COLON
                                || event.getCode() == COMMA
                                || event.getCode() == BACK_SLASH
                                || event.getCode() == ASTERISK
                                || event.getCode() == PERIOD
                                || event.getCode() == MINUS
                                || event.getCode() == OPEN_BRACKET
                                || event.getCode() == CLOSE_BRACKET
                                || event.getCode() == PLUS
                                || event.getCode() == SEMICOLON
                                || event.getCode() == SEPARATOR
                                || event.getCode() == BACK_QUOTE
                                || event.getCode() == QUOTE
                                || event.getCode() == QUOTEDBL
                                || event.getCode() == LESS
                                || event.getCode() == BRACELEFT
                                || event.getCode() == BRACERIGHT
                                || event.getCode() == AT
                                || event.getCode() == DOLLAR
                                || event.getCode() == EURO_SIGN
                                || event.getCode() == EXCLAMATION_MARK
                                || event.getCode() == LEFT_PARENTHESIS
                                || event.getCode() == RIGHT_PARENTHESIS
                                || event.getCode() == UNDERSCORE
                                || event.getCode() == AMPERSAND
                                || event.getCode() == NUMBER_SIGN
                                || event.getCode() == CIRCUMFLEX
                                || event.getCode() == SPACE
                                || event.getCode() == EQUALS

                ) {
                    count++;
                    engine.updateTypedParagraph(input.getText());
                    changeInputTextColor();
                    if (count == characters.length && engine.typedCharactersMatchParagraph()) {
                        calculate_show_scores();

                    }

                }
            }

        }
        //uncommented used for code testing
            System.out.println(characters.length);
            System.out.println(count);
            engine.trackTypeDuration(System.currentTimeMillis() - start);
            System.out.println("type duration: "+ engine.getType_duration());
            System.out.println("character per second: " + engine.getCps());
            System.out.println("words per minute: "+ engine.getWpm());

    }

    private void calculate_show_scores() {
        input.setEditable(false);
        engine.trackTypeDuration(System.currentTimeMillis() - start);
        engine.findScores(input.getText());
        paragraph.setText(
                "Type Duration          : "+ engine.getType_duration()+"\n"+
                "Character Per Second   : " + engine.getCps() +"\n"+
                "Words Per Minute       : "+ engine.getWpm() + "\n"+
                "Error ratio    : "  +errors+" / " + engine.characters.length
        );
    }

    private void changeInputTextColor() {
        if (engine.typedCharactersMatchParagraph()){
            input.setStyle(" -fx-text-fill: black");
            engine.findScores(input.getText());
        }else {
            input.setStyle(" -fx-text-fill: red");
            errors++;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("tutor.fxml"));
        primaryStage.setTitle("Type Tutor");
        primaryStage.setScene(new Scene(root, Screen.getPrimary().getBounds().getWidth()*4/5, Screen.getPrimary().getBounds().getHeight()*3/4));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
