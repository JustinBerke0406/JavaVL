package drawable;

import java.awt.Graphics;

import frame.system.complex.Quaternion;

public class Point extends Drawable {

    public Point(double xPos, double yPos, int width, int height) {
        super(xPos, yPos);
    }

    public Point(double xPos, double yPos, double zPos, int width, int height) {
        super(xPos, yPos, zPos);
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval((int) getCenter().x(), (int) getCenter().y(), 10, 10);
    }

    @Override
    public void rotate(Quaternion q, boolean local) {
        Quaternion.rotate(this, q, local);
    }
}
