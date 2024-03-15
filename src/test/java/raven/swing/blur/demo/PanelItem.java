package raven.swing.blur.demo;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PanelItem extends BlurChild {

    private int panelX;
    private int panelY;


    public PanelItem() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("al center center"));
        JLabel label = new JLabel("Move me");
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "foreground:#FFFFFF;" +
                "font:bold +5");
        add(label);

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
