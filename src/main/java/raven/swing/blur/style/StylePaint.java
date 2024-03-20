package raven.swing.blur.style;

import raven.swing.blur.util.StyleShape;

import java.awt.*;

public interface StylePaint {

    void paint(Component com, Graphics g, StyleShape shape);
}
