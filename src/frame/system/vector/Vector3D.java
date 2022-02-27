package frame.system.vector;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

public class Vector3D extends Vector {
    public Vector3D() {
        super(0, 0, 0);
    }

    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }

    public double x() {
        return getIndex(0);
    }

    public double y() {
        return getIndex(1);
    }

    public double z() {
        return getIndex(2);
    }

    public void setX(double x) {
        setIndex(0, x);
    }

    public void setY(double y) {
        setIndex(1, y);
    }

    public void setZ(double z) {
        setIndex(2, z);
    }
    
    public Vector2D scaleDown() {
        return new Vector2D(x(), y());
    }

    public Vector3D cross(Vector3D other) {
        double x = (y() * other.z()) - (z() * other.y());
        double y = (z() * other.x()) - (x() * other.z());
        double z = (x() * other.y()) - (y() * other.x());

        return new Vector3D(x, y, z);
    }

    public static Vector3D convertLike(Vector like) {
        return new Vector3D(like.getIndex(0), like.getIndex(1), like.getIndex(2));
    }
}
