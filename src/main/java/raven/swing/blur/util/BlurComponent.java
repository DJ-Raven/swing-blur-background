package raven.swing.blur.util;

import com.formdev.flatlaf.ui.FlatUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BlurComponent extends JComponent {

    private int paintCount = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintCount++;
        g.setColor(new Color(252, 252, 252));
        FontMetrics fm = g.getFontMetrics();
        String text = "Paint count : " + paintCount;
        Rectangle2D rec = fm.getStringBounds(text, g);
        g.drawString(text, (int) (getWidth() - rec.getWidth() - 5), fm.getAscent());
    }
}