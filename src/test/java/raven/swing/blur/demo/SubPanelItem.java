package raven.swing.blur.demo;

import raven.swing.blur.BlurChild;
import raven.swing.blur.style.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class SubPanelItem extends BlurChild {

    public SubPanelItem() {
        super(new Style()
                .setBlur(5)
                .setBorder(new StyleBorder(15)
                        .setBorderWidth(1.5f)
                        .setOpacity(0.1f)
                        .setMargin(new Insets(100, 0, 10, 0))
                        .setBorderColor(new GradientColor(
                                Color.decode("#C9D6FF"),
                                Color.decode("#E2E2E2"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ))
                        .setDropShadow(new StyleDropShadow(new Color(0, 0, 0), 0.6f, new Insets(5, 5, 10, 10)))
                )
                .setOverlay(new StyleOverlay(
                        new GradientColor(
                                Color.decode("#C9D6FF"),
                                Color.decode("#E2E2E2"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ), 0.3f))
        );
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        JButton lb = new JButton("Hello");
        //  lb.setOpaque(true);
        // lb.setBackground(new Color(58, 164, 201, 150));
        //   add(lb);
    }
}
