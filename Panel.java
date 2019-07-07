package MiniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;
import java.util.ArrayList;

public class Panel extends JPanel implements Serializable {
   private final int WIDTH; // Width of a frame
   private final int HEIGHT; // Height of a frame

   /* Modes:
   0 - Modifying
   1 - Creating
   2 - Removing
   */
   private int mode = 0;
   public final static int MODIFYING = 0;
   public 	final static int CREATING = 1;
   public final static int REMOVING = 2;

   /* Shapes:
   0 - Rectangle
   1 - Circle
   */
   private int shape = 0;
   public final static int RECTANGLE = 0;
   public final static int CIRCLE = 1;

   private Color color; // Main color. New objects will be drew in this color

   private int toMove = -1; // Object to move
   private int onIndex = -1; // Index of an object that is under the cursor

   private int prevX = -1; // The last position of x
   private int prevY = -1; // The last position of y

   private ArrayList<Shape> objects = new ArrayList<>(); // The list of shapes
   private JFrame colorPicker; // Color picker to pick a color

   private Shape chosen = null; // A chosen object to change its color

   public Panel(int width, int height) {
      WIDTH = width;
      HEIGHT = height;
      color = Color.RED;

      colorPicker = new JFrame("Color Picker");
      colorPicker.setSize(200, 200);
      colorPicker.setResizable(false);
      colorPicker.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose the color picker in the case of closing
      ColorPicker colorPickerPanel = new ColorPicker(this);
      colorPicker.add(colorPickerPanel);
      colorPicker.addMouseListener(colorPickerPanel);
   }

   public int getMode() {
      return mode;
   }

   public int getShape() {
      return shape;
   }

   public int getToMove() {
      return toMove;
   }

   public int getOnIndex() {
      return onIndex;
   }

   public int getPrevX() {
      return prevX;
   }

   public int getPrevY() {
      return prevY;
   }

   public Color getColor() {
      return color;
   }

   public JFrame getColorPicker() {
      return colorPicker;
   }

   public ArrayList<Shape> getObjects() {
      return objects;
   }

   // Change the type of a shape to be drawn to the next one
   public void nextShape() {
      shape = (shape + 1) % 2;
   }

   // Change the mode to the next one
   public void nextMode() {
      mode = (mode + 1) % 3;
   }

   public void setToMove(int toMove) {
      this.toMove = toMove;
   }

   public void setOnIndex(int onIndex) {
      this.onIndex = onIndex;
   }

   public void setPrevX(int prevX) {
      this.prevX = prevX;
   }

   public void setPrevY(int prevY) {
      this.prevY = prevY;
   }

   public void setObjects(ArrayList<Shape> objects) {
      this.objects = objects;
   }

   // Set the index-th object to given object
   public void setObject(int index, Shape obj) {
      objects.set(index, obj);
   }

   // Given an index of an object in list of shapes choose it
   public void setChosenByIndex(int index) {
      if (index < objects.size() && index >= 0)
         chosen = objects.get(index);
      else
         chosen = null;
   }

   public void setColorOfChosen(Color color) {
      if (chosen != null) {
         chosen.color = color;
      }
   }

   public void setColor(Color color) {
      this.color = color;
   }

   // Given the coordinates return an index of an object that is positioned there
   public int getObjectAt(int x, int y) {
      for (int i = objects.size() - 1; i >= 0; i--) {
         if (objects.get(i).isInside(x, y)) {
            return i;
         }
      }
      return -1;
   }

   public void addObject(Shape shape) {
      objects.add(shape);
   }

   // Remove an object given its index
   public void removeObject(int index) {
      if (index < objects.size() && index >= 0)
      objects.remove(index);
   }

   public static void main(String[] args) {
      Panel panel = new Panel(640, 480);
      panel.objects.add(new Rectangle(50, 50, 50, 50, Color.red));

      JFrame jf = new JFrame("Le Paint");
      jf.setSize(panel.WIDTH, panel.HEIGHT);
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminate the program in case of closing
      jf.setResizable(false);
      jf.setVisible(true);

      jf.add(BorderLayout.CENTER, panel);
      jf.addKeyListener(new MyKeyListener(panel));
      jf.addMouseListener(new MyMouseListener(panel));
      jf.addMouseMotionListener(new MyMouseMotionListener(panel));
      panel.repaint();
   }

   @Override
   protected void paintComponent(Graphics g) {
      // Draw a background
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, WIDTH, HEIGHT);

      // Draw all the objects
      for (Shape object : objects) {
         object.show(g);
         if (object == chosen) // If an object is chosen draw a stroke around it
         object.stroke(g);
      }

      // If cursor is on some object draw a stroke around it
      if (onIndex != -1 && onIndex < objects.size()) {
         objects.get(onIndex).stroke(g);
      }

      // Write the name of the mode
      g.setColor(Color.BLACK);
      if (mode == MODIFYING) {
         g.drawString("Modifying", 10, 20);
      } else if (mode == CREATING) {
         g.drawString("Creating", 10, 20);
         if (shape == RECTANGLE) {
            g.drawString("Rectangle", 10, 40);
         } else if (shape == CIRCLE) {
            g.drawString("Circle", 10, 40);
         }
      } else if (mode == REMOVING) {
         g.drawString("Removing", 10, 20);
      }
   }
}
