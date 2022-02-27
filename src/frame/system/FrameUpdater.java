package frame.system;

import frame.Screen;

import javax.swing.Timer;
import java.awt.event.*;

public class FrameUpdater {
    public FrameUpdater(Screen screen) {
        int delay = 10; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                screen.repaint();
            }
        };
        new Timer(delay, taskPerformer).start();
    }
}
