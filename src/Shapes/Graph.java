package Shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    List<Node> nodes;
    List<Edge> edges;

    public Graph(){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }


}
