package drawable;

import java.awt.Graphics;
import java.util.ArrayList;

import frame.system.complex.Quaternion;
import frame.system.vector.Vector3D;

public class Group extends Drawable {
    private ArrayList<Drawable> comps = new ArrayList<>();
    private ArrayList<Vector3D> initCenters = new ArrayList<>();

    public Group() {
        this(0, 0, 0);
    }

    public Group(Drawable... draws) {
        this(0, 0, 0, draws);
    }

    public Group(double x, double y, double z) {
        super(x, y, z);
    }

    public Group(double x, double y, double z, Drawable... draws) {
        super(x, y, z);

        for (Drawable c : draws) {
            initCenters.add(c.getCenter());
            comps.add(c);
        }
    }

    public ArrayList<Drawable> getList() {
        return comps;
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < comps.size(); i++) {
            Drawable c = comps.get(i);
            c.setCenter(Vector3D.convertLike(initCenters.get(i).addElements(getCenter())));
            c.draw(g);
            c.setCenter(Vector3D.convertLike(initCenters.get(i)));
        }
    }

    @Override
    public void rotate(Quaternion q, boolean local) {
        for (int i = 0; i < comps.size(); i++) {
            Drawable c = comps.get(i);
            c.rotate(q, false);
            initCenters.set(i, c.getCenter());
        }
    }
}
