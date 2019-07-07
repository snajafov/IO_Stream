package MiniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColorPicker extends JPanel implements MouseListener {
   private Panel panel; // Main panel

   ColorPicker (Panel panel) {
      this.panel = panel;
      repaint();
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      // Draw a 20x20 color picker
      // y - hue
      // x - saturation
      for (int y = 0; y < 200; y += 10) {
         for (int x = 0; x < 200; x += 10) {
            g.setColor(Color.getHSBColor(y / (float) 200, x / (float) 200, 1));
            g.fillRect(x, y, 10, 10);
         }
      }

      g.setColor(Color.BLACK);
      float[] hsb = new float[3]; // Values of a color in HSB mode
      Color color = panel.getColor();
      Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
      // Draw a rectangle around the main color
      g.drawRect((int) (hsb[1] * 200) / 10 * 10, (int) (hsb[0] * 200) / 10 * 10,10, 10);
   }

   @Override
   public void mouseReleased(MouseEvent mouseEvent) {
      // Set new color and draw a stroke around it on color picker
      Color color = Color.getHSBColor(mouseEvent.getY() / (float) 200,
      mouseEvent.getX() / (float) 200,
      1);
      if (panel.getMode() == Panel.MODIFYING) {
         panel.setColorOfChosen(color);
      }
      panel.setColor(color);
      repaint();
   }

   @Override
   public void mouseClicked(MouseEvent mouseEvent) {}

      @Override
      public void mousePressed(MouseEvent mouseEvent) {}

         @Override
         public void mouseEntered(MouseEvent mouseEvent) {}

            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
            }
