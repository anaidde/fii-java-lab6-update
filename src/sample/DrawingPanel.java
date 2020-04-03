package sample;

import Shapes.*;
import Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image;
    Graphics2D graphics;
    List<Shape> shapeList = new ArrayList<>();
    List<Node> nodeList = new ArrayList<>();
    List<Edge> edgeList = new ArrayList<>();
    Graph graph = new Graph();
    Random random = new Random();
    int counter = 1;
    int index = 0;
    int x1 = 0, y1 = 0;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    public void init() {
        counter = 1;
        shapeList.clear();
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY()); repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        int radius = random.nextInt(200);
        int sides = (int) this.frame.getConfigPanel().sidesField.getValue();
        Color color = new Color((int)(Math.random()*256), (int)(Math.random()*256),(int)(Math.random()*256));
        graphics.setColor(color);
        switch (Objects.requireNonNull(this.frame.getConfigPanel().figuresCombo.getSelectedItem()).toString()) {
            case "Regular Polygon":
                this.frame.getConfigPanel().graphCombo.setVisible(false);
                this.frame.getConfigPanel().graphCombo.setEnabled(false);
                repaint();
                this.shapeList.add(new Shape(x, y, radius, sides, "Regular Polygon", color));
                graphics.fill(new RegularPolygon(x, y, radius, sides));
                break;
            case "Square":
                this.frame.getConfigPanel().graphCombo.setVisible(false);
                this.shapeList.add(new Shape(x, y, radius, sides, "Square", color));
                graphics.fill(new Rectangle(x, y, radius, radius));
                break;
            case "Rectangle":
                this.frame.getConfigPanel().graphCombo.setVisible(false);
                this.shapeList.add(new Shape(x, y, radius, sides, "Rectangle", color));
                graphics.fill(new Rectangle(x, y, radius + radius, radius));
                break;
            case "Circle":
                this.frame.getConfigPanel().graphCombo.setVisible(false);
                this.shapeList.add(new Shape(x, y, radius, sides, "Circle", color));
                graphics.fill(new Ellipse2D.Float(x, y, radius, radius));
                break;
            case "Triangle":
                this.frame.getConfigPanel().graphCombo.setVisible(false);
                this.shapeList.add(new Shape(x, y, radius, sides, "Triangle", color));
                graphics.fill(new RegularPolygon(x, y, radius, sides));
                break;
            case "Graph":
                this.frame.getConfigPanel().sidesField.setEnabled(false);
                this.frame.getConfigPanel().graphCombo.setVisible(true);
                if(Objects.requireNonNull(this.frame.getConfigPanel().graphCombo.getSelectedItem()).toString().equals("Node")){
                    radius = (int) this.frame.getConfigPanel().sizeField.getValue();
                    Node currentNode = new Node(x, y, radius, index);
                    index++;
                    this.graph.addNode(currentNode);
                    this.nodeList.add(currentNode);
                    graphics.setColor(Color.BLACK);
                    graphics.fillOval(x, y, (int) frame.getConfigPanel().sizeField.getValue(), (int) frame.getConfigPanel().sizeField.getValue());
                } else{
                        if(counter%2 == 0){
                            graphics.setColor(Color.BLACK);
                            graphics.drawLine(x1, y1, x, y); counter++;
                            int FirstNode = getNodeIndex(this.nodeList, x, y);
                            int SecondNode = getNodeIndex(this.nodeList, x1, y1);
                            this.edgeList.add(new Edge(SecondNode, FirstNode));
                            System.out.println("(" + FirstNode + ", " + SecondNode + ")");
                        } else {
                            x1 = x;
                            y1 = y; counter++;

                        }
                        System.out.println(counter);
                    }
        }
    }

    public void drawShapesList(){
        this.createOffscreenImage();
        frame.repaint();
        for(Shape shape : this.shapeList) {
            switch (shape.getType()) {
                case "Regular Polygon":
                case "Triangle":
                    graphics.setColor(shape.getColor());
                    graphics.fill(new RegularPolygon(shape.getX(), shape.getY(), shape.getRadius(), shape.getSides()));
                    break;
                case "Square":
                    graphics.setColor(shape.getColor());
                    graphics.fill(new Rectangle(shape.getX(), shape.getY(), shape.getRadius(), shape.getRadius()));
                    break;
                case "Rectangle":
                    graphics.setColor(shape.getColor());
                    graphics.fill(new Rectangle(shape.getX(), shape.getY(), shape.getRadius() + shape.getRadius(), shape.getRadius()));
                    break;
                case "Circle":
                    graphics.setColor(shape.getColor());
                    graphics.fill(new Ellipse2D.Double(shape.getX(), shape.getY(), shape.getRadius(), shape.getRadius()));
                    break;
                case "Graph":
                    this.frame.getConfigPanel().sidesField.setVisible(false);

            }
        }
    }

    @Override
    public void update(Graphics g) { }

    @Override
    protected void paintComponent(Graphics g) {
        //g.drawRect(20, 50, 100, 50);
        g.drawImage(image, 0, 0, this);
    }

    public void deleteShape(){
        int length = shapeList.size();
        this.shapeList.remove(length-1);
        this.drawShapesList();
    }

    public int getNodeIndex(List<Node> nodes, int x1, int y1){
        for(Node i : nodes){
            if(x1 >= i.getX() - i.getRadius() && x1 <= i.getX() + i.getRadius() )
                if(y1 >= i.getY() - i.getRadius() && y1 <= i.getY() + i.getRadius())
                    return i.getIndex();
        }
        return -1;
    }

//    private double repulsiveForce(double x, double k) {
//        return k*k/x;
//    }
//
//    private double attractiveForce(double x, double k) {
//        return x*x/k;
//    }
//
//    public void FruchtermanReingold() {
//        int area = W * H;
//        double k = Math.sqrt(area/this.nodeList.size());
//        for(Node node : this.nodeList) {
//            double displacement = 0;
//            for (Node node1 : this.nodeList) {
//                if (node != node1) {
//                    double distanceX = node1.getX() - node.getX();
//                    double distanceY = node1.getY() - node.getY();
//                    double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
//                    displacement = displacement + (distance / Math.abs(distance)) * repulsiveForce(distance, k);
//                }
//            }
//        }
//    }


}
