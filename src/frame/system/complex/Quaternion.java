package frame.system.complex;

import drawable.Drawable;
import frame.system.vector.Vector;
import frame.system.vector.Vector3D;

public class Quaternion {
    private Vector values;

    public Quaternion(double ww, double aa, double bb, double cc) {
        values = new Vector(ww, aa, bb, cc);
    }

    public Quaternion(Vector v) {
        values = v;
    }

    public Quaternion(double r, Vector v) {
        values = v.expandFirst(1);
        values.setIndex(0, r);
    }

    public static Quaternion add(Quaternion q1, Quaternion q2) {
        return new Quaternion(q1.values.getIndex(0)+q2.values.getIndex(0), 
        q1.values.getIndex(1)+q2.values.getIndex(1), 
        q1.values.getIndex(2)+q2.values.getIndex(2), 
        q1.values.getIndex(3)+q2.values.getIndex(3));
    }

    public static Quaternion subtract(Quaternion q1, Quaternion q2) {
        return new Quaternion(q1.values.getIndex(0)-q2.values.getIndex(0), 
        q1.values.getIndex(1)-q2.values.getIndex(1), 
        q1.values.getIndex(2)-q2.values.getIndex(2), 
        q1.values.getIndex(3)-q2.values.getIndex(3));
    }

    public static Quaternion multiply(Quaternion q1, double q2) {
        return new Quaternion(q1.values.getIndex(0)*q2, 
        q1.values.getIndex(1)*q2, 
        q1.values.getIndex(2)*q2, 
        q1.values.getIndex(3)*q2);
    }

    public static Quaternion multiply(Quaternion q1, Quaternion q2) {
        return hamiltonProduct(q1.values, q2.values);
    }

    public static Quaternion inverse(Quaternion q1) {
        double sum = Math.pow(q1.values.getIndex(0), 2) + Math.pow(q1.values.getIndex(1), 2) + Math.pow(q1.values.getIndex(2), 2) + Math.pow(q1.values.getIndex(3), 2);

        return new Quaternion(q1.values.getIndex(0)/sum, -q1.values.getIndex(1)/sum, -q1.values.getIndex(2)/sum, -q1.values.getIndex(3)/sum);
    }

    public Vector getValues() {
        return values;
    }

    public static Quaternion rotationQuaternion(Vector3D axis, double angle) {
        double sin = Math.sin(angle/2);
        
        return new Quaternion(Math.cos(angle/2), axis.transform((num) -> sin * num));
    }
    
    public static void rotate(Drawable comp, Vector3D axis, double angle, boolean local) {   
        Quaternion P = new Quaternion(0, comp.getCenter());
        Quaternion R = rotationQuaternion(axis, angle);
        Quaternion Ri = inverse(R);
        Quaternion result;

        if (local)
            result = multiply(multiply(R, P), Ri);
        else
            result = multiply(multiply(Ri, P), R);

        comp.setCenter(new Vector3D(result.getValues().getIndex(1), result.getValues().getIndex(2), result.getValues().getIndex(3)));
    }

    public static void rotate(Drawable comp, Quaternion rotation, boolean local) {   
        Quaternion P = new Quaternion(0, comp.getCenter());
        Quaternion R = rotation;
        Quaternion Ri = inverse(R);
        Quaternion result;

        if (local)
            result = multiply(multiply(R, P), Ri);
        else
            result = multiply(multiply(Ri, P), R);

        comp.setCenter(new Vector3D(result.getValues().getIndex(1), result.getValues().getIndex(2), result.getValues().getIndex(3)));
    }

    public static Quaternion hamiltonProduct(Vector q1, Vector q2) {
        if (q1.getSize() != 4 || q2.getSize() != 4) 
            throw new IllegalArgumentException("Vectors must be of length 4");
        
        double a1 = q1.getIndex(0), a2 = q2.getIndex(0);
        double b1 = q1.getIndex(1), b2 = q2.getIndex(1);
        double c1 = q1.getIndex(2), c2 = q2.getIndex(2);
        double d1 = q1.getIndex(3), d2 = q2.getIndex(3);
        
        double one = a1*a2 - b1*b2 - c1*c2 - d1*d2;
        double two = a1*b2 + b1*a2 + c1*d2 - d1*c2;
        double three = a1*c2 - b1*d2 + c1*a2 + d1*b2;
        double four = a1*d2 + b1*c2 - c1*b2 + d1*a2;

        return new Quaternion(one, two, three, four);
    }
}
