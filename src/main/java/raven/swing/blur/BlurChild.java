package raven.swing.blur;

import javax.swing.*;
import java.awt.*;

public class BlurChild extends JComponent implements BlurChildData {

    public BlurChild() {
        init();
    }

    private void init() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        BlurData data = getBlurData(this);
        if (data != null) {
            g.drawImage(data.getBlurImageAt(getBounds()), 0, 0, null);
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
