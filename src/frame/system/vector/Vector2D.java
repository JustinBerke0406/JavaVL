package frame.system.vector;

public class Vector2D extends Vector{
    public Vector2D() {
        super(0, 0);
    }

    public Vector2D(double x, double y) {
        super(x, y);
    }

    public double x() {
        return getIndex(0);
    }

    public double y() {
        return getIndex(1);
    }

    public void setX(double x) {
        setIndex(0, x);
    }

    public void setY(double y) {
        setIndex(1, y);
    }

    public Vector3D scaleUp(double z) {
        return new Vector3D(x(), y(), z);
    }

    public Vector3D scaleUp() {
        return scaleUp(0);
    }
}
