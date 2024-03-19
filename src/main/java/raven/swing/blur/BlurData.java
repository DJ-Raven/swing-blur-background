package raven.swing.blur;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface BlurData extends BlurChildData {

    BufferedImage getBlurImageAt(Shape shape, float blur);
}
