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
                        .setBorderWidth(1.5f)
                        .setOpacity(0.1f)
                        .setBorderColor(new GradientColor(
                                Color.decode("#C9D6FF"),
                                Color.decode("#E2E2E2"),
                                new Point2D.Float(0, 0),
                                new Point2D.Float(1, 1)
                        ))
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
        setLayout(new MigLayout());
    }
}
