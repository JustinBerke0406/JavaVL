package drawable;

import java.awt.Graphics;

import frame.system.complex.Quaternion;

public class Point extends Drawable {
    private int width;
    private int height;

    public Point(double xPos, double yPos, int width, int height) {
        super(xPos, yPos);
    }

    public Point(double xPos, double yPos, double zPos, int width, int height) {
        super(xPos, yPos, zPos);
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval((int) getCenter().x(), (int) getCenter().y(), width, height);
    }

    @Override
    public void rotate(Quaternion q, boolean local) {
        Quaternion.rotate(this, q, local);
    }
}
