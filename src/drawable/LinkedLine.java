package drawable;

import java.awt.Graphics;

import frame.system.complex.Quaternion;
import frame.system.vector.Vector3D;

public class LinkedLine extends Drawable {
    private Drawable start;
    private Drawable end;

    private Vector3D temp;

    public LinkedLine(Drawable one, Drawable two) {
        super(one.getCenter().x(), one.getCenter().y());

        start = one;
        end = two;

        temp = getCenter();
    }

    @Override
    public void draw(Graphics g) {
        Vector3D center = start.getCenter();

        int diffX = (int) (getCenter().x() - temp.x());
        int diffY = (int) (getCenter().y() - temp.y());

        g.drawLine((int) center.x() + diffX, (int) (center.y() + diffY), (int) (end.getCenter().x() + diffX), (int) (end.getCenter().y() + diffY));
        
        setCenter(Vector3D.convertLike(center.deepCopy()));
        getCenter().setX(getCenter().x() + diffX);
        getCenter().setY(getCenter().y() + diffY);

        temp = Vector3D.convertLike(center.deepCopy());
    }

    @Override
    public void rotate(Quaternion q, boolean local) {

    }
}
