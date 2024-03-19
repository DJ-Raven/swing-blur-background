package raven.swing.blur;

import com.formdev.flatlaf.util.UIScale;
import com.twelvemonkeys.image.ImageUtil;
import raven.swing.blur.style.StyleOverlay;
import raven.swing.blur.util.BlurComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class BlurBackground extends BlurComponent implements BlurData {

    private Image image;
    protected BufferedImage buffImage;
    protected Map<Float, BufferedImage> blurImages;

    private StyleOverlay overlay;

    private int oldWidth;
    private int oldHeight;

    public BlurBackground() {
        init();
    }

    public BlurBackground(Image image) {
        this.image = image;
        init();
    }

    public BlurBackground(LayoutManager layout) {
        setLayout(layout);
        init();
    }

    private void init() {
        blurImages = new HashMap<>();
        setOpaque(true);
    }

    @Override
    public BufferedImage getBlurImageAt(Shape shape, float blur) {
        if (blur > 0) {
            BufferedImage blurImage;
            if (blurImages.containsKey(blur)) {
                blurImage = blurImages.get(blur);
            } else {
                if (buffImage == null) {
                    return null;
                }
                blurImage = createBlurImage(buffImage, blur);
                blurImages.put(blur, blurImage);
            }
            return getImage(blurImage, shape);
        } else {
            return getImageAt(shape);
        }
    }

    @Override
    public BufferedImage getImageAt(Shape shape) {
        return getImage(buffImage, shape);
    }

    @Override
    public Component getSource() {
        return this;
    }

    private BufferedImage getImage(BufferedImage image, Shape shape) {
        if (shape instanceof Rectangle2D) {
            Rectangle rec = shape.getBounds();
            fixRectangle(rec, image);
            return image.getSubimage(rec.x, rec.y, rec.width, rec.height);
        }
        Rectangle rec = shape.getBounds();
        BufferedImage recImage = getImage(image, rec);
        return createShapeImage(recImage, shape);
    }

    private void fixRectangle(Rectangle rec, BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        if (rec.x < 0) {
            rec.width += rec.x;
            rec.x = 0;
        }
        if (rec.y < 0) {
            rec.height += rec.y;
            rec.y = 0;
        }
        if (rec.x + rec.width > width) {
            rec.width = width - rec.x;
        }
        if (rec.y + rec.height > height) {
            rec.height = height - rec.y;
        }
    }

    private BufferedImage createShapeImage(BufferedImage image, Shape shape) {
        int width = image.getWidth();
        int height = image.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle rec = shape.getBounds();
        AffineTransform oldTran = g2.getTransform();
        double x = rec.getX() < 0 ? 0 : -rec.getX();
        double y = rec.getY() < 0 ? 0 : -rec.getY();
        g2.translate(x, y);
        g2.fill(shape);
        g2.setTransform(oldTran);
        g2.setComposite(AlphaComposite.SrcIn);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return buffImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        boolean paint = updateImage();
        if (paint) {
            g.drawImage(buffImage, 0, 0, null);
        }
        super.paintComponent(g);
    }

    private boolean updateImage() {
        int width = getWidth();
        int height = getHeight();
        if (image == null || width == 0 || height == 0) {
            return false;
        }
        if (buffImage == null || oldWidth != width || oldHeight != height) {
            int imgWidth = image.getWidth(null);
            int imgHeight = image.getHeight(null);
            buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buffImage.createGraphics();
            Rectangle rectangle = getAutoSize(new Dimension(width, height), new Dimension(imgWidth, imgHeight));
            Image newImage = new ImageIcon(image.getScaledInstance(rectangle.width, rectangle.height, Image.SCALE_SMOOTH)).getImage();
            g2.drawImage(newImage, rectangle.x, rectangle.y, null);
            Shape shape = new Rectangle2D.Double(0, 0, width, height);
            if (overlay != null && overlay.getOpacity() > 0) {
                overlay.paint(this, g2, shape);
            }
            g2.dispose();
            oldWidth = width;
            oldHeight = height;
            blurImages.clear();
        }
        return true;
    }

    private BufferedImage createBlurImage(BufferedImage image, float blur) {
        return ImageUtil.blur(image, UIScale.scale(blur));
    }

    private Rectangle getAutoSize(Dimension size, Dimension target) {
        int width = size.width;
        int height = size.height;
        if (width > target.width) {
            width = target.width;
        }
        if (height > target.height) {
            height = target.height;
        }
        int iw = target.width;
        int ih = target.height;
        double xScale = (double) width / iw;
        double yScale = (double) height / ih;
        double scale = Math.max(xScale, yScale);
        int scaleWidth = (int) (scale * iw);
        int scaleHeight = (int) (scale * ih);
        int x = (getWidth() - scaleWidth) / 2;
        int y = (getHeight() - scaleHeight) / 2;
        return new Rectangle(new Point(x, y), new Dimension(scaleWidth, scaleHeight));
    }


    public BlurData getBlurData() {
        return this;
    }

    public StyleOverlay getOverlay() {
        return overlay;
    }

    public void setOverlay(StyleOverlay overlay) {
        this.overlay = overlay;
        this.buffImage = null;
        repaint();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.buffImage = null;
        repaint();
    }
}
