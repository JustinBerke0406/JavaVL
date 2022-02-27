import drawable.Group;
import drawable.Point;
import frame.Screen;
import frame.system.FrameUpdater;
import frame.system.complex.ComplexNumber;
import frame.system.complex.Quaternion;
import frame.system.vector.Vector;
import frame.system.vector.Vector3D;
import javax.swing.Timer;
import java.awt.event.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        Screen window = new Screen(500, 500, 200, 200, "Testing Window", true);
        FrameUpdater update = new FrameUpdater(window);

        /*
         * Point p1 = new Point(240, 240, 0, 10);
         * Point p2 = new Point(240, 260, 0, 10);
         * Point p3 = new Point(260, 240, 0, 10);
         * Point p4 = new Point(260, 260, 0, 10);
         * Point p5 = new Point(240, 240, 20, 10, 10);
         * Point p6 = new Point(240, 260, 20, 10, 10);
         * Point p7 = new Point(260, 240, 20, 10, 10);
         * Point p8 = new Point(260, 260, 20, 10, 10);
         */

        Point p1 = new Point(-10, -10, -10, 10);
        Point p2 = new Point(-10, 10, -10, 10);
        Point p3 = new Point(10, -10, -10, 10);
        Point p4 = new Point(10, 10, -10, 10);
        Point p5 = new Point(-10, -10, 10, 10, 10);
        Point p6 = new Point(10, -10, 10, 10, 10);
        Point p7 = new Point(-10, 10, 10, 10, 10);
        Point p8 = new Point(10, 10, 10, 10, 10);

        Group gr = new Group(100, 100, 0, p1, p2, p3, p4, p5, p6, p7, p8);

        Quaternion R = Quaternion.rotationQuaternion(Vector3D.convertLike(new Vector(0, 1, 0).unitVector()),
                Math.PI / 64);

        /*
         * Quaternion.rotate(p1, R, true);
         * Quaternion.rotate(p2, R, true);
         * Quaternion.rotate(p3, R, true);
         * Quaternion.rotate(p4, R, true);
         * Quaternion.rotate(p5, R, true);
         * Quaternion.rotate(p6, R, true);
         * Quaternion.rotate(p7, R, true);
         * Quaternion.rotate(p8, R, true);
         */

        window.getInstance(0).add(gr);

        window.toggleWindow();

        int delay = 10; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /*
                 * Quaternion.rotate(p1, R, true);
                 * Quaternion.rotate(p2, R, true);
                 * Quaternion.rotate(p3, R, true);
                 * Quaternion.rotate(p4, R, true);
                 * Quaternion.rotate(p5, R, true);
                 * Quaternion.rotate(p6, R, true);
                 * Quaternion.rotate(p7, R, true);
                 * Quaternion.rotate(p8, R, true);
                 */

                gr.rotate(R, true);
            }
        };
        new Timer(delay, taskPerformer).start();
    }
}
