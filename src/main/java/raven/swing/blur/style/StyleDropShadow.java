package raven.swing.blur.style;

import com.formdev.flatlaf.util.UIScale;
import raven.swing.blur.util.StyleShape;
import raven.swing.blur.util.Utils;
import raven.swing.shadow.ShadowRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StyleDropShadow implements StylePaint {

    private Color color;
    private float opacity;
    private Insets insets;

    private int oldWidth;
    private int oldHeight;

    private BufferedImage buffImage;

    public StyleDropShadow(Color color, float opacity, Insets insets) {
        this.color = color;
        this.opacity = opacity;
        this.insets = insets;
    }

    public Color getColor() {
        return color;
    }

    public float getOpacity() {
        return opacity;
    }

    public Insets getInsets() {
        return UIScale.scale(insets);
    }

    @Override
    public void paint(Component com, Graphics g, StyleShape shape) {
        Dimension size = com.getBounds().getSize();
        if (size.width == 0 || size.height == 0) {
            return;
        }
        int shadowSize = Utils.getMaxInsert(getInsets()) * 2;
        Shape shapeImage = shape.getStyle().getBorder().createShape(new Rectangle(new Dimension(size.width - shadowSize, size.height - shadowSize)));
        if (buffImage == null || size.width != oldWidth || size.height != oldHeight) {
            buffImage = createShadowBorder(size.width, size.height, shapeImage, shape.getShape());
            oldWidth = size.width;
            oldHeight = size.height;
        }
        if (buffImage != null) {
            g.drawImage(buffImage, 0, 0, null);
        }
    }

    private BufferedImage createShadowBorder(int w, int h, Shape shapeImage, Shape shape) {
        int shadowSize = Utils.getMaxInsert(getInsets());
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        ShadowRenderer shadowRenderer = new ShadowRenderer(shadowSize, opacity, color);
        g2.drawImage(shadowRenderer.createShadow(createImage(shapeImage)), 0, 0, null);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(shape);
        g2.dispose();
        return image;
    }

    private BufferedImage createImage(Shape shape) {
        Rectangle rec = shape.getBounds();
        int width = rec.width;
        int height = rec.height;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(color);
        g2.fill(shape);
        g2.dispose();
        return image;
    }
}
