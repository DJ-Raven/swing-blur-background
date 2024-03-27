package raven.swing.blur.demo;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.geom.Point2D;

public class Demo extends JFrame {

    public Demo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(800, 500)));
        setLocationRelativeTo(null);

        BlurBackground background = new BlurBackground(new ImageIcon(getClass().getResource("/raven/swing/blur/background.jpg")).getImage());
        background.setOverlay(new StyleOverlay(new GradientColor(
                Color.decode("#000000"),
                Color.decode("#2B5A68"),
                new Point2D.Float(0f, 0f),
                new Point2D.Float(1f, 0f)
        ), 0.4f));

        background.setLayout(new MigLayout("insets 20 50 20 50"));

        JPanel simplePanel1 = new JPanel(new MigLayout("insets 30 50 10 10"));
        simplePanel1.add(new JLabel("Simple Panel 1"), "pos 0 0");
        simplePanel1.setBorder(new LineBorder(new Color(131, 78, 215)));
        simplePanel1.setOpaque(false);


        JPanel simplePanel2 = new JPanel(new MigLayout("al center center"));
        simplePanel2.add(new JLabel("Simple Panel 2"), "pos 0 0");
        simplePanel2.setBorder(new LineBorder(new Color(243, 57, 57)));
        simplePanel2.setOpaque(false);

        simplePanel2.add(new PanelItem(), "width 300, height 250");

        background.add(simplePanel1, "width 100%,height 100%");
        simplePanel1.add(simplePanel2, "width 100%,height 100%");
        add(background);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        System.setProperty("flatlaf.uiScale", "1f");
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new Demo().setVisible(true));
    }
}
