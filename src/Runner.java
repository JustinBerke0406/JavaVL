import drawable.Shape;
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

        int[][] square = { { -10, -10, -10, 1, 2, 4 },
                { -10, 10, -10, 3, 6 },
                { 10, -10, -10, 3, 5 },
                { 10, 10, -10, 7 },
                { -10, -10, 10, 5 },
                { 10, -10, 10, 7 },
                { -10, 10, 10, 4, 7 },
                { 10, 10, 10 } };

        int[][] triangle = { { -10, 0, -10, 1, 2 },
                { -10, 0, 10, 3 },
                { 10, 0, -10, 3 },
                { 10, 0, 10 },
                { 0, 10, 0, 0, 1, 2, 3 } };

        Shape squareObj = new Shape(100, 100, 0, square);
        Shape triObj = new Shape(100, 110, 0, triangle);

        squareObj.attach(triObj);

        Quaternion R = Quaternion.rotationQuaternion(Vector3D.convertLike(new Vector(1, 1, 0).unitVector()),
                Math.PI / 64);

        window.getInstance(0).add(squareObj);

        window.toggleWindow();
        
        int delay = 10; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                squareObj.rotate(Quaternion.rotationQuaternion(Vector3D.convertLike(new Vector(1 * 2*(Math.random()-0.5), 1 *2*(Math.random()-0.5), 1 * 2*(Math.random()-0.5)).unitVector()),
                Math.PI / 64), true);
            }
        };
        new Timer(delay, taskPerformer).start();
    }
}
