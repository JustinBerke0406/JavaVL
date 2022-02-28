import drawable.Group;
import drawable.LinkedLine;
import drawable.Point;
import frame.Screen;
import frame.system.FrameUpdater;
import frame.system.complex.Quaternion;
import frame.system.vector.Vector;
import frame.system.vector.Vector3D;
import javax.swing.Timer;
import java.awt.event.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        Screen window = new Screen(500, 500, 200, 200, "Testing Window", true);
        new FrameUpdater(window);

        Point p1 = new Point(-10, -10, -10, 0, 0);
        Point p2 = new Point(-10, 10, -10, 0, 0);
        Point p3 = new Point(10, -10, -10, 0, 0);
        Point p4 = new Point(10, 10, -10, 0, 0);
        Point p5 = new Point(-10, -10, 10, 0, 0);
        Point p6 = new Point(10, -10, 10, 0, 0);
        Point p7 = new Point(-10, 10, 10, 0, 0);
        Point p8 = new Point(10, 10, 10, 0, 0);

        LinkedLine p12 = new LinkedLine(p1, p2);
        LinkedLine p13 = new LinkedLine(p1, p3);
        LinkedLine p15 = new LinkedLine(p1, p5);
        LinkedLine p24 = new LinkedLine(p2, p4);
        LinkedLine p27 = new LinkedLine(p2, p7);
        LinkedLine p34 = new LinkedLine(p3, p4);
        LinkedLine p48 = new LinkedLine(p4, p8);
        LinkedLine p56 = new LinkedLine(p5, p6);
        LinkedLine p68 = new LinkedLine(p6, p8);
        LinkedLine p78 = new LinkedLine(p7, p8);
        LinkedLine p75 = new LinkedLine(p7, p5);
        LinkedLine p36 = new LinkedLine(p3, p6);

        Group gr = new Group(100, 100, 0, p1, p2, p3, p4, p5, p6, p7, p8, p12, p13, p15, p24, p27, p34, p48, p56, p68, p78, p75, p36);

        Quaternion R = Quaternion.rotationQuaternion(Vector3D.convertLike(new Vector(1, 0, 1).unitVector()),
                Math.PI / 64);

        window.getInstance(0).add(gr);

        window.toggleWindow();

        int delay = 10; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gr.rotate(R, true);
            }
        };
        new Timer(delay, taskPerformer).start();
    }
}
