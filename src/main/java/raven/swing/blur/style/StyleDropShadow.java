package raven.swing.blur.style;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import raven.swing.blur.util.Utils;
import raven.swing.shadow.ShadowRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StyleDropShadow implements StylePaint {

    private Color color;
    private float opacity;
    private Insets insets;

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
    public void paint(Component com, Graphics g, Shape shape) {
        Dimension size = com.getBounds().getSize();
        int shadowSize = Utils.getMaxInsert(getInsets()) * 2;
        //  test arc
        float arc = UIScale.scale(20);
        Shape shapeImage = FlatUIUtils.createRoundRectanglePath(0, 0, size.width - shadowSize, size.height - shadowSize, arc, arc, arc, arc);
        g.drawImage(createShadowBorder(size.width, size.height, shapeImage, shape), 0, 0, null);
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
