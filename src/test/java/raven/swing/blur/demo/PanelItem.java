package raven.swing.blur.demo;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class PanelItem extends BlurChild {

    public PanelItem() {
        super(new Style()
                .setBlur(20f)
                .setBorder(new StyleBorder(20)
                        .setBorderWidth(1.5f)
                        .setOpacity(0.1f)
                        .setBorderColor(new GradientColor(
                                Color.decode("#C9D6FF"),
                                Color.decode("#E2E2E2"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ))
                        .setDropShadow(new StyleDropShadow(new Color(0, 0, 0), 0.6f, new Insets(0, 0, 15, 15)))
                )

                .setOverlay(new StyleOverlay(
                        new GradientColor(
                                Color.decode("#C9D6FF"),
                                Color.decode("#E2E2E2"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ), 0.1f))
        );
        init();
    }

    private void init() {
        setLayout(new MigLayout("insets 10 0 0 0,wrap", "[]"));
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
        //  add(new SubPanelItem(), "width 200,height 150");
    }
}
