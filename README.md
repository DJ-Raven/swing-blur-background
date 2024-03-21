# Swing Blur Background

Blur background custom using java swing with flatlaf, include style shadow border, gradient border, gradient overlay

<img src="https://github.com/DJ-Raven/swing-blur-background/blob/main/screenshot/sample.jpg" width="300" alt="sample"/>

## Installation
This project library do not available in maven central. so you can install with the jar library
- Copy jar library file to the root project. exp : `library/swing-blur-background-1.0.0.jar`
- Add this code to `pom.xml`
``` xml
<dependency>
    <groupId>raven.swing</groupId>
    <artifactId>swing-blur-background</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>${basedir}/library/swing-blur-background-1.0.0.jar</systemPath>
</dependency>
```
- Other library are use with this library
``` xml
<dependency>
  <groupId>com.twelvemonkeys.common</groupId>
  <artifactId>common-image</artifactId>
  <version>3.10.1</version>
</dependency>

<dependency>
  <groupId>com.formdev</groupId>
  <artifactId>flatlaf</artifactId>
  <version>3.4</version>
</dependency>

<dependency>
  <groupId>com.formdev</groupId>
  <artifactId>flatlaf-extras</artifactId>
  <version>3.4</version>
</dependency>
```
## Create blur background
``` java
// create blur background component
ImageIcon icon = new ImageIcon(getClass().getResource("/raven/swing/blur/background.jpg"));
BlurBackground background = new BlurBackground(icon.getImage());

// set overlay style
background.setOverlay(new StyleOverlay(new GradientColor(
        Color.decode("#000000"),
        Color.decode("#2B5A68"),
        new Point2D.Float(0f, 0f),
        new Point2D.Float(1f, 0f)
), 0.4f));
```
## Create style
``` java
Style style = new Style()
        .setBlur(20f)
        .setBorder(new StyleBorder(20)
                .setBorderWidth(1.5f)
                .setOpacity(0.1f)
                .setBorderColor(new GradientColor(
                        Color.decode("#C9D6FF"),
                        Color.decode("#E2E2E2"),
                        new Point2D.Float(0, 0),
                        new Point2D.Float(1, 1)
                ))
                .setDropShadow(new StyleDropShadow(
                        new Color(0, 0, 0),
                        0.6f,
                        new Insets(0, 0, 15, 15)
                ))
        )

        .setOverlay(new StyleOverlay(
                new GradientColor(
                        Color.decode("#C9D6FF"),
                        Color.decode("#E2E2E2"),
                        new Point2D.Float(0, 0),
                        new Point2D.Float(1, 1)
                ), 0.1f)
        );
```
## Create blur child
``` java
BlurChild blurChild=new BlurChild(style);

background.add(blurChild);
// or extends class
public class BlurChildItem extends BlurChild {
  public BlurChildItem() {
    super(new Style());
  }
}
```
## Style support
- [x] Border gradient color
- [x] Dropshadow border
- [x] Overlay gradient color
