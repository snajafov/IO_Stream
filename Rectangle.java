package MiniProject;

import java.awt.*;

public class Rectangle extends Shape {
   private int width;
   private int height;

   public Rectangle(int x, int y, int width, int height) {
      if (width < 0) {
         this.x = x + width;
         this.width = -width;
      } else {
         this.x = x;
         this.width = width;
      }
      if (height < 0) {
         this.y = y + height;
         this.height = -height;
      } else {
         this.y = y;
         this.height = height;
      }
   }

   public Rectangle(int x, int y, int width, int height, Color color) {
      if (width < 0) {
         this.x = x + width;
         this.width = -width;
      } else {
         this.x = x;
         this.width = width;
      }
      if (height < 0) {
         this.y = y + height;
         this.height = -height;
      } else {
         this.y = y;
         this.height = height;
      }
      this.color = color;
   }

   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }

   public void show(Graphics g) {
      g.setColor(color);
      g.fillRect(x, y, width, height);
   }

   public void stroke(Graphics g) {
      g.setColor(Color.BLACK);
      g.drawRect(x, y, width, height);
   }

   public boolean isInside(int x, int y) {
      return !(x > this.x + width || x < this.x || y > this.y + height || y < this.y);
   }
}
