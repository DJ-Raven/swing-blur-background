package raven.swing.blur.demo;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Random;

public class PanelItem extends BlurChild {

    private int panelX;
    private int panelY;


    public PanelItem() {
        super(new Style()
                .setBorder(new StyleBorder(50, 30, 30, 50)
                        .setBorderWidth(2)
                        .setOpacity(0.5f)
                        .setBorderColor(new GradientColor(
                                Color.decode("#009FFF"),
                                Color.decode("#ec2F4B"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ))
                )
                .setOverlay(new StyleOverlay(
                        new GradientColor(
                                Color.decode("#757F9A"),
                                Color.decode("#D7DDE8"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ), 0.1f))
        );
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,al center center", "[center]"));
        JLabel label = new JLabel("Move me");
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +5");
        add(label);
        add(new JTextField());
        JButton button = new JButton("Random Text");
        button.addActionListener(e -> {
            Random ran = new Random();
            label.setText("Move me " + ran.nextInt(999));
        });
        add(button);

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
