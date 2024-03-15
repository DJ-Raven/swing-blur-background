package raven.swing.blur;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface BlurData {

    BufferedImage getBlurImageAt(Shape shape);

    BufferedImage getImageAt(Shape shape);

    BufferedImage getOutlineImage(Shape shape);
}
