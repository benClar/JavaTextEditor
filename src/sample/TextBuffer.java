package sample;

/**
 * Created by benjaminclarke on 10/04/2017.
 */
public class TextBuffer {

    private StringBuilder text;

    public TextBuffer(){

        this.text = new StringBuilder("");
    }

    public TextBuffer(String s){
        this.text = new StringBuilder(s);
    }

    public String getText(){
        return this.text.toString();
    }

    public void append(String s){
        this.text.append(s);
    }

    public void insert(int cursor, String s){
        this.text.insert(cursor, s);
    }

    public void delete(int cursorStart, int cursorEnd){
        if(getText().compareTo("") == 0){
            return;
        }
        if(cursorStart == cursorEnd){
            if(cursorStart == 0){
                return;
            }
            this.text.deleteCharAt(cursorStart - 1);
        } else {
            this.text.delete(cursorStart, cursorEnd);
        }
    }
}
