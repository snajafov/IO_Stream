package MiniProject;

import java.awt.*;

public class Circle extends Shape {
   private int r; // Radius of a circle

   Circle(int x, int y, int r) {
      this.x = x;
      this.y = y;
      this.r = Math.abs(r);
   }

   Circle(int x, int y, int r, Color color) {
      this.x = x;
      this.y = y;
      this.r = Math.abs(r);
      this.color = color;
   }

   @Override
   public void show(Graphics g) {
      g.setColor(color);
      g.fillOval(x - r, y - r, 2 * r, 2 * r);
   }

   @Override
   public void stroke(Graphics g) {
      g.setColor(Color.BLACK);
      g.drawOval(x - r, y - r, 2 * r, 2 * r);
   }

   @Override
   public boolean isInside(int x, int y) {
      return (Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y)) < Math.sqrt(r * r));
   }
}
