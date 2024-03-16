package raven.swing.blur.style;

import java.awt.*;

public class Style implements StylePaint {

    private StyleBorder border;

    public Style() {
    }

    public Style setBorder(StyleBorder style) {
        this.border = style;
        return this;
    }

    public StyleBorder getBorder() {
        return border;
    }

    @Override
    public void paint(Component com, Graphics g) {
        if (border != null) {
            border.paint(com, g);
        }
    }
}
