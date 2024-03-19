package raven.swing.blur;

import raven.swing.blur.style.Style;
import raven.swing.blur.util.BlurComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BlurChild extends BlurComponent implements BlurChildData {

    private Style style;

    public BlurChild() {
        init();
    }

    public BlurChild(Style style) {
        this();
        this.style = style;
    }

    private void init() {
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        BlurData data = getBlurData(this);
        if (data != null) {
            Rectangle bound = getBounds();
            Rectangle rec = SwingUtilities.convertRectangle(getParent(), bound, data.getSource());
            Shape shape = style == null ? rec : style.getBorder() == null ? rec : style.getBorder().createShape(rec);
            int x = rec.x >= 0 ? 0 : rec.x * -1;
            int y = rec.y >= 0 ? 0 : rec.y * -1;

            //  create outside background image
            if (!(shape instanceof Rectangle2D)) {
                Image image = data.getImageAt(rec);
                if (image != null) {
                    g.drawImage(image, x, y, null);
                }
            }

            Image image = data.getBlurImageAt(shape, style.getBlur());
            if (image != null) {
                g.drawImage(image, x, y, null);
            }
            if (style != null) {
                Shape defaultShape = style == null ? rec : style.getBorder() == null ? rec : style.getBorder().createShape(new Rectangle(bound.getSize()));
                style.paint(this, g, defaultShape);
            }
        }
        super.paintComponent(g);
    }

    @Override
    public Insets getInsets() {
        if (style != null && style.getBorder() != null) {
            return style.getBorder().getInsets();
        }
        return super.getInsets();
    }

    private BlurData getBlurData(Component component) {
        if (component.getParent() != null) {
            if (component.getParent() instanceof BlurData) {
                return (BlurData) component.getParent();
            } else {
                return getBlurData(component.getParent());
            }
        } else {
            return null;
        }
    }
}
