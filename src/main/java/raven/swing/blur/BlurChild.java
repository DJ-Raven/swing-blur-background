package raven.swing.blur;

import com.formdev.flatlaf.ui.FlatUIUtils;
import raven.swing.blur.style.Style;
import raven.swing.blur.util.BlurComponent;
import raven.swing.blur.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BlurChild extends BlurComponent implements BlurChildData {

    private Style style;
    private BufferedImage buffImage;

    public BlurChild() {
        init();
    }

    public BlurChild(Style style) {
        this();
        this.style = style;
    }

    private void init() {
        setOpaque(true);
        installMouseMove();
    }

    @Override
    public BufferedImage getImageAt(Shape shape) {
        if (buffImage == null) {
            return null;
        }
        Rectangle rec = shape.getBounds();
        Utils.fixRectangle(rec, buffImage);
        return buffImage.getSubimage(rec.x, rec.y, rec.width, rec.height);
    }

    @Override
    public Style getStyle() {
        return style;
    }

    @Override
    public Component getSource() {
        return this;
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage paintImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = paintImage.createGraphics();
        Rectangle bound = getBounds();
        boolean isRec = isRectangle();
        float blur = style.getBlur();

        // create background image
        BlurChildData blurChildData = getBlurChildData(this);
        if (!isRec && isOpaque()) {
            Rectangle rec = SwingUtilities.convertRectangle(getParent(), bound, blurChildData.getSource());
            int x = rec.x >= 0 ? 0 : rec.x * -1;
            int y = rec.y >= 0 ? 0 : rec.y * -1;

            Image image = blurChildData.getImageAt(rec);
            if (image != null) {
                g2.drawImage(image, x, y, null);
            }
        }
        if (blurChildData.getStyle() != null) {
            blur += blurChildData.getStyle().getBlur();
        }
        BlurData blurData = getBlurData(this);
        if (blurData != null) {
            Insets insets = style == null || style.getBorder() == null || style.getBorder().getDropShadow() == null ? new Insets(0, 0, 0, 0) : style.getBorder().getDropShadow().getInsets();
            Rectangle blurRec = FlatUIUtils.subtractInsets(bound, insets);
            Rectangle rec = SwingUtilities.convertRectangle(getParent(), blurRec, blurData.getSource());
            Shape shape = style == null ? rec : style.getBorder() == null ? rec : style.getBorder().createShape(rec);
            Image image = blurData.getBlurImageAt(shape, blur);
            int x = rec.x >= 0 ? 0 : rec.x * -1;
            int y = rec.y >= 0 ? 0 : rec.y * -1;
            if (image != null) {
                g2.drawImage(image, insets.left + x, insets.top + y, null);
            }
            if (style != null) {
                Rectangle r;
                if (style.getBorder() == null || style.getBorder().getDropShadow() == null) {
                    r = new Rectangle(bound.getSize());
                } else {
                    r = FlatUIUtils.subtractInsets(new Rectangle(bound.getSize()), style.getBorder().getDropShadow().getInsets());
                }
                Shape defaultShape = style.getBorder() == null ? r : style.getBorder().createShape(r);
                style.paint(this, g2, defaultShape);
            }
        }
        g2.dispose();
        g.drawImage(paintImage, 0, 0, null);
        buffImage = paintImage;
        super.paintComponent(g);
    }

    @Override
    public Insets getInsets() {
        if (style != null && style.getBorder() != null) {
            if (style.getBorder().getDropShadow() != null) {
                return FlatUIUtils.addInsets(style.getBorder().getInsets(), style.getBorder().getDropShadow().getInsets());
            } else {
                return style.getBorder().getInsets();
            }
        }
        return super.getInsets();
    }

    private boolean isRectangle() {
        return style == null || style.getBorder() == null || style.getBorder().isRectangle();
    }

    private BlurData getBlurData(Component component) {
        if (component.getParent() != null) {
            if (component.getParent() instanceof BlurData) {
                return (BlurData) component.getParent();
            } else {
                return getBlurData(component.getParent());
            }
        } else {
            return null;
        }
    }

    private BlurChildData getBlurChildData(Component component) {
        if (component.getParent() != null) {
            if (component.getParent() instanceof BlurChildData) {
                return (BlurChildData) component.getParent();
            } else {
                return getBlurChildData(component.getParent());
            }
        } else {
            return null;
        }
    }
}
