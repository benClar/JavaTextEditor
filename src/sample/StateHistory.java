package sample;

import javax.xml.soap.Text;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by benjaminclarke on 10/04/2017.
 */
public class StateHistory {

    private LinkedList<TextEditorState> history;

    private int current;

    public StateHistory(){
        this.history = new LinkedList<TextEditorState>();
    }

    public void push(TextEditorState s){
        chop();
        this.history.push(s);
        current = 0;
        logPosition();
    }

    private void chop(){
        int end = 0;
        while(current != 0 && end != current){
            pop();
            end += 1;
        }
    }

    public TextEditorState pop(){
        return this.history.pop();
    }

    public TextEditorState current(){
        return this.history.get(this.current);
    }

    public void moveForward(){
        if(current != 0) {
            current -= 1;
        }
        logPosition();
    }

    public int size(){
        return this.history.size();
    }

    public void moveBack(){
        if(current != size() - 1){
            current += 1;
        }
        logPosition();
    }

    private void logPosition(){
        System.out.println( String.format("State : %d/%d", current + 1, size()));
    }

    public void replaceCurrent(TextEditorState s){
        this.history.remove(this.current);
        this.history.add(this.current, s);
    }

    public Boolean isAtHead(){
        return this.current == 0;
    }


}
