package drawable;

import java.util.ArrayList;

import frame.system.vector.Vector3D;

public class Shape extends Group {
    private int[][] matrix;

    public Shape(int x, int y, int z, int[][] m) {
        super(x, y, z, generateDrawables(m));
        
        matrix = m;
    }

    public void attach(Shape shape) {
        for (Drawable p : shape.getList()) {
            p.getCenter().setX(p.getCenter().x() + shape.getCenter().x() - getCenter().x());
            p.getCenter().setY(p.getCenter().y() + shape.getCenter().y() - getCenter().y());
            p.getCenter().setZ(p.getCenter().z() + shape.getCenter().z() - getCenter().z());
        }
        
        getList().addAll(shape.getList());
    }

    private static Drawable[] generateDrawables(int[][] m) {
        int height = m.length;
        int width;

        int[][] lineStorage = new int[height][];

        ArrayList<Drawable> args = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            width = m[i].length;

            if (width < 3) throw new IndexOutOfBoundsException("Invalid shape matrix (Row " + i + " is less than 3 elements)");

            args.add(new Point(m[i][0], m[i][1], m[i][2], 0, 0));
            int[] lineTemp = new int[width - 3];

            for (int z = 3; z < width; z++)
                lineTemp[z-3] = m[i][z];

            lineStorage[i] = lineTemp;
        }

        for (int i = 0; i < height; i++)
            for (int index : lineStorage[i])
                args.add(new LinkedLine(args.get(i), args.get(index)));
        
        Drawable[] ret = new Drawable[0];
        return args.toArray(ret);
    }
}
