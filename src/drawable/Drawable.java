package drawable;

import java.awt.Graphics;

import frame.system.complex.Quaternion;
import frame.system.vector.Vector2D;
import frame.system.vector.Vector3D;

public abstract class Drawable {
    private Vector3D center;

    public Drawable(double xPos, double yPos, double zPos) {
        center = new Vector3D(xPos, yPos, zPos);
    }

    public Drawable(double xPos, double yPos) {
        this(xPos, yPos, 0);
    }

    public Drawable(Vector3D vector) {
        center = vector;
    }

    public Drawable(Vector2D vector) {
        center = vector.scaleUp();
    }

    public void setCenter(Vector3D v) {
        center = v;
    }

    public Vector3D getCenter() {
        return center;
    }

    public abstract void draw(Graphics g);

    public abstract void rotate(Quaternion q, boolean local);
}
