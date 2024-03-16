package raven.swing.blur;

import raven.swing.blur.style.Style;
import raven.swing.blur.util.BlurComponent;

import javax.swing.*;
import java.awt.*;

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
        // paint background
        // g.setColor(new Color(182, 180, 180));
        // g.fillRect(0, 0, getWidth(), getHeight());

        BlurData data = getBlurData(this);
        if (data != null) {
            Rectangle bound = getBounds();
            Rectangle rec = SwingUtilities.convertRectangle(getParent(), bound, data.getSource());
            Shape shape = style == null ? rec : style.getBorder() == null ? rec : style.getBorder().createShape(rec);
            int x = rec.x >= 0 ? 0 : rec.x * -1;
            int y = rec.y >= 0 ? 0 : rec.y * -1;
            Image image = data.getBlurImageAt(shape);
            if (image != null) {
                g.drawImage(image, x, y, null);
            }
            if (style != null) {
                style.paint(this, g);
            }
        }
        super.paintComponent(g);
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
