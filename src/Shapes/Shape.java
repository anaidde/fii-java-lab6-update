package Shapes;

import java.awt.*;


public class Shape {
   int x;
   int y;
   int radius;
   int sides;
   String type;
   Color color;
   public Shape(int x, int y, int radius, int sides, String type, Color color){
       this.x = x;
       this.y = y;
       this.radius = radius;
       this.sides = sides;
       this.type = type;
       this.color = color;
   }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getSides() {
        return sides;
    }

    public String getType() {
        return type;
    }

    public Color getColor() {
       return color;
    }

    public String toString() {
       return "Object type : " + this.type + " situated in (" + this.x + " ," + this.y + ") of size : " + this.radius;
   }
}
