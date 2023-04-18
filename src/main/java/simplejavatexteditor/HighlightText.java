package simplejavatexteditor;

import javax.swing.text.*;
import java.awt.*;

public class HighlightText extends DefaultHighlighter.DefaultHighlightPainter {

    public HighlightText(Color color) {
        super(color);
    }

    public void highLight(JTextComponent textComp, String[] pattern) {
        try {
            Highlighter highlighter = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            for (String s : pattern) {
                int pos = 0;

                while ((pos = text.indexOf(s, pos)) >= 0) {
                    highlighter.addHighlight(pos, pos + s.length(), this);
                    pos += s.length();
                }
            }
        } catch (BadLocationException ignored) {
        }

    }

    public void removeHighlights(JTextComponent textComp) {

        Highlighter highlighter = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = highlighter.getHighlights();

        for (Highlighter.Highlight hilite : hilites) {
            if (hilite.getPainter() instanceof HighlightText) {
                highlighter.removeHighlight(hilite);
            }
        }
    }
}
