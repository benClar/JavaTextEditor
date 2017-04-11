package sample;

import javafx.scene.input.KeyCode;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by benjaminclarke on 10/04/2017.
 */
public class TextEditorTest extends TestCase {

    @Test
    public void testCopyState() {
        TextBuffer t = new TextBuffer("Testing");
        TextEditorState s1 = new TextEditorState(t);
        TextEditorState s2 = new TextEditorState(t);
        s2.getTextBuffer().append("2");
        assertEquals(s1.getTextBuffer().getText(), "Testing");
        assertEquals(s2.getTextBuffer().getText(), "Testing2");
    }

    @Test
    public void testDeleteHighlight() {
        TextBuffer t = new TextBuffer("Testing");
        t.delete(3,7);
        assertEquals(t.getText(), "Tes");
        TextBuffer t2 = new TextBuffer("Testing");
        t2.delete(6,7);
        assertEquals(t2.getText(), "Testin");
    }

    @Test
    public void testDeleteSingle() {
        TextBuffer t = new TextBuffer("Testing");
        t.delete(7,7);
        assertEquals("Testin", t.getText());
        TextBuffer t2 = new TextBuffer("K");
        t2.delete(1,1);
        assertEquals("", t2.getText());
    }

    @Test
    public void testRollState() {
        Tab tab = new Tab();
        tab.type(KeyCode.A, "A");
        tab.type(KeyCode.B, "B");
        tab.type(KeyCode.C, "C");
        assertEquals(2, tab.getStateHistory().size());
        tab.type(KeyCode.SPACE, " ");
        assertEquals(3, tab.getStateHistory().size());
        tab.type(KeyCode.D, "D");
        tab.type(KeyCode.E, "E");
        tab.type(KeyCode.F, "F");
        assertEquals(3, tab.getStateHistory().size());
        tab.command(KeyCode.Z, "Z");
        assertEquals(3, tab.getStateHistory().size());
        assertEquals("ABC", tab.getStateHistory().current().getTextBuffer().getText());
        tab.command(KeyCode.Z, "Z");
        assertEquals("", tab.getStateHistory().current().getTextBuffer().getText());
    }

    @Test
    public void testCtrlZOnEmpty() {
        Tab tab = new Tab();
        tab.command(KeyCode.Z, "Z");
        tab.type(KeyCode.A, "A");
        tab.type(KeyCode.B, "B");
        tab.type(KeyCode.C, "C");
        assertEquals(2, tab.getStateHistory().size());
        tab.type(KeyCode.SPACE, " ");
        assertEquals(3, tab.getStateHistory().size());
        tab.type(KeyCode.D, "D");
        tab.type(KeyCode.E, "E");
        tab.type(KeyCode.F, "F");
        assertEquals(3, tab.getStateHistory().size());
        tab.command(KeyCode.Z, "Z");
        assertEquals(3, tab.getStateHistory().size());
        assertEquals("ABC", tab.getStateHistory().current().getTextBuffer().getText());
        tab.command(KeyCode.Z, "Z");
        assertEquals("", tab.getStateHistory().current().getTextBuffer().getText());
    }

    @Test
    public void testHistoryTraverse(){
        StateHistory his = new StateHistory();
        TextEditorState s1 = new TextEditorState(new TextBuffer("a"));
        TextEditorState s2 = new TextEditorState(new TextBuffer("b"));
        TextEditorState s3 = new TextEditorState(new TextBuffer("c"));
        TextEditorState s4 = new TextEditorState(new TextBuffer("d"));
        his.push(s1);
        assertEquals("a", his.current().getTextBuffer().getText());
        his.push(s2);
        assertEquals("b", his.current().getTextBuffer().getText());
        his.moveBack();
        assertEquals("a", his.current().getTextBuffer().getText());
        his.moveForward();
        assertEquals("b", his.current().getTextBuffer().getText());
        his.push(s3);
        assertEquals("c", his.current().getTextBuffer().getText());
        his.moveBack();
        his.moveBack();
        his.push(s4);
        assertEquals("d", his.current().getTextBuffer().getText());
        his.moveBack();
        his.moveForward();
        assertEquals("d", his.current().getTextBuffer().getText());
    }


}