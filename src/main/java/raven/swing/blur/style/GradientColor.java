package raven.swing.blur.style;

import java.awt.*;
import java.awt.geom.Point2D;

public class GradientColor implements StylePaint {

    private Color startColor;
    private Color endColor;

    private Point2D.Float startPoint;
    private Point2D.Float endPoint;

    public GradientColor(Color color) {
        startColor = color;
    }

    public GradientColor(Color startColor, Color endColor, Point2D.Float startPoint, Point2D.Float endPoint) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public GradientColor setColor(Color color) {
        this.startColor = color;
        return this;
    }

    public GradientColor setColor(Color startColor, Color endColor, Point2D.Float startPoint, Point2D.Float endPoint) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        return this;
    }

    public Color getStartColor() {
        return startColor;
    }

    public Color getEndColor() {
        return endColor;
    }

    public Point2D.Float getStartPoint() {
        return startPoint;
    }

    public Point2D.Float getEndPoint() {
        return endPoint;
    }

    @Override
    public void paint(Component com, Graphics g, Shape shape) {
        if (endPoint == null) {
            g.setColor(startColor);
        } else {
            float width = com.getWidth();
            float height = com.getHeight();
            float x1 = startPoint.x * width;
            float y1 = startPoint.y * height;
            float x2 = endPoint.x * width;
            float y2 = endPoint.y * height;
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new GradientPaint(x1, y1, startColor, x2, y2, endColor));
        }
    }
}
