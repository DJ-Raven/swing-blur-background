package raven.swing.blur;

import raven.swing.blur.util.BlurComponent;

import javax.swing.*;
import java.awt.*;

public class BlurChild extends BlurComponent implements BlurChildData {

    public BlurChild() {
        init();
    }

    private void init() {
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        BlurData data = getBlurData(this);
        if (data != null) {
            Rectangle rec = getBounds();
            rec = SwingUtilities.convertRectangle(getParent(), rec, data.getSource());
            int x = rec.x >= 0 ? 0 : rec.x * -1;
            int y = rec.y >= 0 ? 0 : rec.y * -1;
            g.drawImage(data.getBlurImageAt(rec), x, y, null);

            // test
            g.setColor(new Color(225, 210, 194));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
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
