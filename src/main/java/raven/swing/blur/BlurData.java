package raven.swing.blur;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface BlurData {

    BufferedImage getBlurImageAt(Shape shape, float blur);

    BufferedImage getImageAt(Shape shape);

    Component getSource();
}
