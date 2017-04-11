package sample;

import org.junit.Test;

/**
 * Created by benjaminclarke on 10/04/2017.
 */
public class CursorPosition {

    private Integer start;

    public void setEnd(Integer end) {
        this.end = end;
    }

    private Integer end;

    private static CursorPosition cp = null;

    public void setStart(Integer start) {
        this.start = start;
    }

    private CursorPosition(){
        this.start = 0;
        this.end = 0;
    }

    public void increment(){
        this.start += 1;
        this.end += 1;
    }

    public void decrement(int cursorStart, int cursorEnd){
        if (cursorStart == cursorEnd){
            this.start -= 1;
            this.end -= 1;
        } else {
            this.end = this.start;
        }

    }

    public static CursorPosition create(){
        if(CursorPosition.cp == null) {
            CursorPosition.cp = new CursorPosition();
        }
        return CursorPosition.cp;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Boolean isHightlighted(){
        return this.start != this.end;
    }

    public void setToEnd(TextEditorState s){
        this.start = this.end = s.getTextBuffer().getText().length();

    }


}
