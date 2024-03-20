package raven.swing.blur.util;

import raven.swing.blur.style.Style;

import java.awt.*;

public class StyleShape {

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public StyleShape(Shape shape, Style style) {
        this.shape = shape;
        this.style = style;
    }

    public StyleShape(Shape shape) {
        this.shape = shape;
    }

    private Shape shape;
    private Style style;
}
