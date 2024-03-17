package raven.swing.blur.style;

import java.awt.*;

public class Style implements StylePaint {

    private StyleBorder border;
    private StyleOverlay overlay;

    public Style() {
    }

    public Style setBorder(StyleBorder style) {
        this.border = style;
        return this;
    }

    public Style setOverlay(StyleOverlay style) {
        this.overlay = style;
        return this;
    }

    public StyleBorder getBorder() {
        return border;
    }

    public StyleOverlay getOverlay() {
        return overlay;
    }

    @Override
    public void paint(Component com, Graphics g, Shape shape) {
        if (overlay != null && overlay.getOpacity() > 0f) {
            overlay.paint(com, g, shape);
        }
        if (border != null && border.getBorderWidth() > 0) {
            border.paint(com, g, shape);
        }
    }
}
