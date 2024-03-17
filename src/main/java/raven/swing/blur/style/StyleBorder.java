package raven.swing.blur.style;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;

import java.awt.*;

public class StyleBorder implements StylePaint {

    private float arcTopLeft;
    private float arcTopRight;
    private float arcBottomLeft;
    private float arcBottomRight;

    private float borderWidth;

    private StylePaint borderColor;
    private float opacity = 1f;

    public StyleBorder(float arc) {
        arcTopLeft = arcTopRight = arcBottomLeft = arcBottomRight = arc;
    }

    public StyleBorder(float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
        this.arcTopLeft = arcTopLeft;
        this.arcTopRight = arcTopRight;
        this.arcBottomLeft = arcBottomLeft;
        this.arcBottomRight = arcBottomRight;
    }

    public StyleBorder setArc(float arc) {
        arcTopLeft = arcTopRight = arcBottomLeft = arcBottomRight = arc;
        return this;
    }

    public StyleBorder setArc(float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
        this.arcTopLeft = arcTopLeft;
        this.arcTopRight = arcTopRight;
        this.arcBottomLeft = arcBottomLeft;
        this.arcBottomRight = arcBottomRight;
        return this;
    }

    public StyleBorder setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public StyleBorder setBorderColor(Color color) {
        borderColor = new GradientColor(color);
        return this;
    }

    public StyleBorder setBorderColor(GradientColor color) {
        borderColor = color;
        return this;
    }

    public StyleBorder setOpacity(float opacity) {
        this.opacity = opacity;
        return this;
    }

    public StylePaint getBorderColor() {
        return borderColor;
    }

    public float getOpacity() {
        return opacity;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public Shape createShape(Rectangle rec) {
        if (arcTopLeft == 0 && arcTopRight == 0 && arcBottomLeft == 0 && arcBottomRight == 0) {
            return rec;
        } else {
            float topLeft = UIScale.scale(arcTopLeft);
            float topRight = UIScale.scale(arcTopRight);
            float bottomLeft = UIScale.scale(arcBottomLeft);
            float bottomRight = UIScale.scale(arcBottomRight);
            return FlatUIUtils.createRoundRectanglePath(rec.x, rec.y, rec.width, rec.width, topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    public Shape createBorder(Rectangle rec) {
        if (arcTopLeft == 0 && arcTopRight == 0 && arcBottomLeft == 0 && arcBottomRight == 0) {
            float border = UIScale.scale(borderWidth);
            return FlatUIUtils.createRoundRectangle(rec.x, rec.y, rec.width, rec.height, border, 0, 0, 0, 0);
        } else {
            float border = UIScale.scale(borderWidth);
            float topLeft = UIScale.scale(arcTopLeft);
            float topRight = UIScale.scale(arcTopRight);
            float bottomLeft = UIScale.scale(arcBottomLeft);
            float bottomRight = UIScale.scale(arcBottomRight);
            return FlatUIUtils.createRoundRectangle(rec.x, rec.y, rec.width, rec.height, border, topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    @Override
    public void paint(Component com, Graphics g, Shape shape) {
        if (opacity > 0) {
            Graphics2D g2 = (Graphics2D) g.create();
            FlatUIUtils.setRenderingHints(g2);
            if (borderColor != null) {
                borderColor.paint(com, g2, shape);
            } else {
                g2.setColor(new Color(114, 117, 114));
            }
            if (opacity < 1) {
                g2.setComposite(AlphaComposite.SrcOver.derive(opacity));
            }
            g2.fill(createBorder(new Rectangle(com.getSize())));
            g2.dispose();
        }
    }
}
