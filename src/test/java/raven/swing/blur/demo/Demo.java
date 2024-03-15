package raven.swing.blur.demo;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;

import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {

    public Demo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setLocationRelativeTo(null);

        BlurBackground background = new BlurBackground(new ImageIcon(getClass().getResource("/raven/swing/blur/background.jpg")).getImage());
        background.setLayout(new MigLayout());

        add(background);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        // System.setProperty("flatlaf.uiScale", "0.8f");
        FlatMacLightLaf.setup();
        EventQueue.invokeLater(() -> new Demo().setVisible(true));
    }
}
