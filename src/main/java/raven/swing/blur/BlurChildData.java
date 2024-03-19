package raven.swing.blur;

import raven.swing.blur.style.Style;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface BlurChildData {

    BufferedImage getImageAt(Shape shape);

    Style getStyle();

    Component getSource();
}
