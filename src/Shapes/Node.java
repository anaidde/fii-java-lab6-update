package Shapes;

import javafx.scene.shape.Ellipse;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Node{
    double x;
    double y;
    double radius;
    int index;

    public Node(double x, double y, double radius, int index) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.index = index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea(){
        return 9.85*this.radius*this.radius;
    }

    public int getIndex() {
        return index;
    }
}
