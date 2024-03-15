package raven.swing.blur.demo;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurChild;

import javax.swing.*;

public class PanelItem extends BlurChild {

    public PanelItem() {
        init();
    }

    private void init() {
        setLayout(new MigLayout());
        add(new JButton("Hello"));
    }
}
