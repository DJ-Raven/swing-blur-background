package raven.swing.blur.demo;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import java.awt.*;
import java.awt.geom.Point2D;

public class SubPanelItem extends BlurChild {

    public SubPanelItem() {
        super(new Style()
                .setBlur(10)
                .setBorder(new StyleBorder(30, 30, 30, 30)
                        .setBorderWidth(1)
                        .setOpacity(0.8f)
                        .setBorderColor(new GradientColor(
                                Color.decode("#141E30"),
                                Color.decode("#243B55"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ))
                )
                .setOverlay(new StyleOverlay(
                        new GradientColor(
                                Color.decode("#141E30"),
                                Color.decode("#243B55"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ), 0.1f))
        );
        init();
    }

    private void init() {
        setLayout(new MigLayout());
    }
}
