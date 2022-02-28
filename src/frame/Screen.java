package frame;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import drawable.Drawable;
import drawable.Group;
import frame.system.complex.Quaternion;
import frame.system.vector.Vector3D;

import java.awt.event.*;
import java.util.ArrayList;

public class Screen extends JPanel {
    private JFrame frame;

    private int width;
    private int height;
    private int gridWidth;
    private int gridHeight;

    private String title;

    private boolean resizable;
    private boolean shown = false;

    private Graphics graphics;

    private ArrayList<Layer> layers = new ArrayList<>();

    /**
     * Creates a new Screen object. Screen objects host all components of JavaVL.
     * <br><br>
     * <i>*Defaults gridWidth and gridHeight to the actual window size</i>
     * <br><br>
     * 
     * @param width Width of the screen in pixels
     * @param height Height of the screen in pixels
     * @param title Window name
     * @param resizable If the window can be resized
     * 
     * 
     */
    public Screen(int width, int height, String title, boolean resizable) {
        this(width, height, width, height, title, resizable);
    }

    /**
     * Creates a new Screen object. Screen objects host all components of JavaVL.
     * 
     * @param width Width of the screen in pixels
     * @param height Height of the screen in pixels
     * @param title Window name
     * @param resizable If the window can be resized
     * @param gridWidth Width of the custom coordinate system
     * @param gridHeight Height of the custom coordinate system
     */
    public Screen(int width, int height, int gridWidth, int gridHeight, String title, boolean resizable) {
        this.frame = new JFrame(title);
        this.title = title;
        this.width = width;
        this.height = height;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.resizable = resizable;

        setup();
    }

    /**
     * Toggles the visibility of the screen.
     */
    public void toggleWindow() {
        this.frame.setVisible(this.shown = !this.shown);
        this.graphics = this.frame.getGraphics();
    }

    private void setup() {
        this.frame.getContentPane().add(this);
        this.frame.setSize(this.width, this.height);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setResizable(this.resizable);
        // this.frame.setUndecorated(true);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                width = componentEvent.getComponent().getWidth();
                height = componentEvent.getComponent().getHeight();
            }
        });
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGridWidth() {
        return this.gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public int getGridHeight() {
        return this.gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isResizable() {
        return this.resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isShown() {
        return this.shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public Graphics getG() {
        return this.graphics;
    }

    public void setG(Graphics g) {
        this.graphics = g;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public void setLayers(ArrayList<Layer> layers) {
        this.layers = layers;
    }

    private void gridToPixel(Drawable p) {
        Vector3D center = p.getCenter();

        center.setX(center.x() * (double) width/gridWidth);
        center.setY(center.y() * (double) height/gridHeight);
        center.setZ(center.z() * ((double) height/gridHeight));

        if (p instanceof Group) {
            for (Drawable c : ((Group) p).getList()) {
                gridToPixel(c);
            }
        }
    }

    /**
    * Gets the instance of the Layer class with the given ID. If no such instance exists, one is created.
    * 
    * @param id
    * @return Layer object
    */
    public Layer getInstance(int id) {
        if (id < layers.size() && layers.get(id) != null) return layers.get(id);
        else {
            Layer newLayer = new Layer(id);
            layers.add(newLayer);

            return newLayer;
        }
    }

    public class Layer extends Drawable {
        private int id;
        private ArrayList<Drawable> components = new ArrayList<>();
    
        private Layer(int id) {
            super(0, 0, 0);
            this.id = id;
        }

        public int getID() {
            return id;
        }
        
        public ArrayList<Drawable> getComponents() {
            return components;
        }

        public void setComponents(ArrayList<Drawable> components) {
            this.components = components;
        }

        public void add(Drawable... comp) {
            for (Drawable c : comp) {
                gridToPixel(c);
                components.add(c);
            }   
        }

        @Override
        public void draw(Graphics g) {
            for (Drawable c : components) c.draw(g);
        }

        @Override
        public void rotate(Quaternion q, boolean local) {
            for (Drawable c : components) c.rotate(q, local);;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        layers.get(0).draw(g);
    }
}

