package MiniProject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyMouseMotionListener implements MouseMotionListener {
   private Panel panel; // Main panel

   public MyMouseMotionListener(Panel panel) {
      this.panel = panel;
   }

   @Override
   public void mouseDragged(MouseEvent mouseEvent) {
      if (panel.getMode() == Panel.MODIFYING) {
         int toMove = panel.getToMove(); // Index of an object to be moved
         if (toMove >= 0 && toMove < panel.getObjects().size()) { // If there is any object to move then move it
            Shape obj = panel.getObjects().get(toMove);
            obj.move(mouseEvent.getX() - panel.getPrevX(), mouseEvent.getY() - panel.getPrevY());
            panel.setObject(toMove, obj);
         }
         // Reset previous coordinates
         panel.setPrevX(mouseEvent.getX());
         panel.setPrevY(mouseEvent.getY());
      } else if (panel.getMode() == Panel.CREATING) { // If mode is creating then draw a shape of given size and color
         int width = mouseEvent.getX() - panel.getPrevX();
         int height = mouseEvent.getY() - panel.getPrevY();

         int objSize = panel.getObjects().size();
         int prevX = panel.getPrevX();
         int prevY = panel.getPrevY();
         Color color = panel.getColor();
         if (panel.getShape()== Panel.RECTANGLE) {
            panel.setObject(objSize - 1, new Rectangle(
            prevX, prevY,
            width, height, color
            ));
         } else {
            panel.setObject(objSize - 1, new Circle(
            prevX, prevY,
            width, color
            ));
         }

      }

      panel.repaint();
   }

   @Override
   public void mouseMoved(MouseEvent mouseEvent) { // If cursor is moved change the value of onIndex
      panel.setOnIndex(panel.getObjectAt(mouseEvent.getX(), mouseEvent.getY()));
      panel.repaint();
   }
}
