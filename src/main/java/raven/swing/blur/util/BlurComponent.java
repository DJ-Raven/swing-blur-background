package raven.swing.blur.util;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
}
