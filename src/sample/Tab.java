package sample;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.*;
import javafx.scene.input.KeyCode;



/**
 * Created by benjaminclarke on 10/04/2017.
 */
public class Tab {

    private Scene scene;

    public StateHistory getStateHistory() {
        return stateHistory;
    }

    private StateHistory stateHistory;
    private CursorPosition cursorPosition;
    private Text text;

    public Tab(Parent root){
        this.scene = new Scene(root, 300, 275);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyPressed(event);
            }
        });
        initialise();
        updateText();
    }

    public Tab(){
        /*Headless Tab for testing*/
        initialise();
        updateText();
    }

    private void initialise(){
        stateHistory = new StateHistory();
        stateHistory.push(new TextEditorState());
        this.cursorPosition = CursorPosition.create();
        this.text = new Text();
        this.text.setFont(new Font(20));
    }

    public Scene getScene(){
        return this.scene;
    }

    private void KeyPressed (KeyEvent event){

        if(event.isAltDown()){

        }
        else if(event.isControlDown()) {

        }
        else if(event.isShortcutDown()) {
            command(event.getCode(), event.getText());
        }
        else{
            type(event.getCode(), event.getText());
        }
        updateText();
    }

    public void command(KeyCode code, String text){
        System.out.println(code.getName());
        System.out.println("=======");
        switch (code) {
            case Z:
                stateHistory.moveBack();
                cursorPosition.setToEnd(stateHistory.current());
                break;
            case Y:
                stateHistory.moveForward();
                cursorPosition.setToEnd(stateHistory.current());
                break;
            default:
                break;

        }
    }

    public void type(KeyCode code, String text){
        System.out.println(String.format("Key Stroke: %s",code.getName()));
        TextEditorState newState = new TextEditorState(stateHistory.current());

        if (text.compareTo("") != 0) {
            if (this.cursorPosition.isHightlighted() == Boolean.FALSE) {
                newState.getTextBuffer().insert(this.cursorPosition.getStart(), text);
                this.cursorPosition.increment();
                if(code == KeyCode.SPACE || this.stateHistory.size() == 1 || !this.stateHistory.isAtHead()){
                    addNewState(newState);
                } else {
                    this.stateHistory.replaceCurrent(newState);
                }
            }

        } else {
            if (code == KeyCode.BACK_SPACE) {
                newState.getTextBuffer().delete(this.cursorPosition.getStart(), this.cursorPosition.getEnd());
                this.cursorPosition.decrement(this.cursorPosition.getStart(), this.cursorPosition.getEnd());
                addNewState(newState);
            } else {
                assert (code == KeyCode.SHIFT);
            }
        }

    }

    private void addNewState(TextEditorState newState){
        if(!newState.getTextBuffer().getText().equals(stateHistory.current().getTextBuffer().getText())){
            stateHistory.push(newState);
        }
    }

    private void updateText(){
        text.setText(stateHistory.current().getTextBuffer().getText());
    }

    public Text getText(){
        return this.text;
    }
}
