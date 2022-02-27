package frame;

import frame.system.vector.*;

public abstract class Placeable {
    private Vector3D center;

    public Placeable(double xPos, double yPos, double zPos) {
        center = new Vector3D(xPos, yPos, zPos);
    }

    public Placeable(double xPos, double yPos) {
        this(xPos, yPos, 0);
    }

    public Placeable(Vector3D vector) {
        center = vector;
    }

    public Placeable(Vector2D vector) {
        center = vector.scaleUp();
    }

    public void setCenter(Vector3D v) {
        center = v;
    }

    public Vector3D getCenter() {
        return center;
    }
}
