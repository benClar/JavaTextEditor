package sample;

/**
 * Created by benjaminclarke on 10/04/2017.
 */
public class TextEditorState {

    private TextBuffer text;

    public TextEditorState(){
        this.text = new TextBuffer();
    }

    public TextEditorState(TextEditorState s){
        this.text = s.getTextBufferCopy();
    }

    public TextEditorState(TextBuffer tb){
        this.text = new TextBuffer(tb.getText());
    }

    public TextBuffer getTextBuffer(){
        return this.text;
    }

    private TextBuffer getTextBufferCopy(){
        return new TextBuffer(this.text.getText());
    }


}
