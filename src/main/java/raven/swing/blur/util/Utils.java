package raven.swing.blur.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {

    public static void fixRectangle(Rectangle rec, BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        if (rec.x < 0) {
            rec.width += rec.x;
            rec.x = 0;
        }
        if (rec.y < 0) {
            rec.height += rec.y;
            rec.y = 0;
        }
        if (rec.x + rec.width > width) {
            rec.width = width - rec.x;
        }
        if (rec.y + rec.height > height) {
            rec.height = height - rec.y;
        }
    }
}
