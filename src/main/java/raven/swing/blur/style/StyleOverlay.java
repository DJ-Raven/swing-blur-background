package raven.swing.blur.style;

import com.formdev.flatlaf.ui.FlatUIUtils;

import java.awt.*;

public class StyleOverlay implements StylePaint {

    private GradientColor color;
    private float opacity;

    public StyleOverlay(Color color, float opacity) {
        this.color = new GradientColor(color);
        this.opacity = opacity;
    }

    public StyleOverlay(GradientColor color, float opacity) {
        this.color = color;
        this.opacity = opacity;
    }

    public GradientColor getColor() {
        return color;
    }

    public float getOpacity() {
        return opacity;
    }

    @Override
    public void paint(Component com, Graphics g, Shape shape) {
        Graphics2D g2 = (Graphics2D) g.create();
        color.paint(com, g2, shape);
        FlatUIUtils.setRenderingHints(g2);
        if (opacity < 1) {
            g2.setComposite(AlphaComposite.SrcOver.derive(opacity));
        }
        g2.fill(shape);
        g2.dispose();
    }
}
