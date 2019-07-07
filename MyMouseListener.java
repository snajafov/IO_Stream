package MiniProject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
   private Panel panel; // Main panel

   MyMouseListener(Panel panel) {
      this.panel = panel;
   }

   @Override
   public void mousePressed(MouseEvent mouseEvent) {
      // Set previous coordinates
      panel.setPrevX(mouseEvent.getX());
      panel.setPrevY(mouseEvent.getY());
      if (panel.getMode() == Panel.MODIFYING) {
         // Set the chosen object and an object to move
         panel.setChosenByIndex(panel.getOnIndex());
         panel.setToMove(panel.getObjectAt(panel.getPrevX(), panel.getPrevY()));
      } else if (panel.getMode() == Panel.CREATING) {
         // Draw an object based on a shape value
         if (panel.getShape() == Panel.RECTANGLE) {
            panel.addObject(new Rectangle(mouseEvent.getX(), mouseEvent.getY(), 0, 0, panel.getColor()));
         } else if (panel.getShape() == Panel.CIRCLE) {
            panel.addObject(new Circle(mouseEvent.getX(), mouseEvent.getY(), 0, panel.getColor()));
         }
      } else if (panel.getMode() == Panel.REMOVING) {
         // Remove the object under the cursor
         int toRemove = panel.getObjectAt(panel.getPrevX(), panel.getPrevY());
         if (toRemove != -1) {
            panel.removeObject(toRemove);
         }
         // Reset onIndex to not have a problem with drawing a stroke on a non-existing or incorrect object
         panel.setOnIndex(-1);
      }
      panel.repaint();
   }

   @Override
   public void mouseClicked(MouseEvent mouseEvent) {}

      @Override
      public void mouseReleased(MouseEvent mouseEvent) {}

         @Override
         public void mouseEntered(MouseEvent mouseEvent) {}

            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
            }
