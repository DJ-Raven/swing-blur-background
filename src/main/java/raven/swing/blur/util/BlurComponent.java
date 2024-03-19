package raven.swing.blur.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class BlurComponent extends JComponent {

    private int panelX;
    private int panelY;

    public BlurComponent() {
        init();
    }

    private void init() {
        setOpaque(true);
    }

    public void installMouseMove() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                panelX = e.getX();
                panelY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - panelX;
                int deltaY = e.getY() - panelY;
                setLocation(getX() + deltaX, getY() + deltaY);
            }
        };
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    private int paintCount = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintCount++;
        g.setColor(new Color(252, 252, 252));
        FontMetrics fm = g.getFontMetrics();
        String text = "Paint count : " + paintCount;
        Rectangle2D rec = fm.getStringBounds(text, g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawString(text, (int) (getWidth() - rec.getWidth() - 5), fm.getAscent());
        g2.dispose();
    }
}
