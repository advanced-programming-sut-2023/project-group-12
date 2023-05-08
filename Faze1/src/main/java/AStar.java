import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private List<Node> path;

    public AStar(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void run(Node startNode, Node endNode) {
        PriorityQueue<Node> open = new PriorityQueue<>();
        List<Node> closed = new ArrayList<>();

        startNode.setG(0);
        startNode.setH(heuristic(startNode, endNode));
        startNode.setF(startNode.getG() + startNode.getH());
        open.add(startNode);

        while (!open.isEmpty()) {
            Node currentNode = open.poll();

            if (currentNode.equals(endNode)) {
                path = new ArrayList<>();
                while (currentNode != null) {
                    path.add(currentNode);
                    currentNode = currentNode.getParent();
                }
                Collections.reverse(path);
                break;
            }

            closed.add(currentNode);

            for (Edge edge : edges) {
                if (edge.getSource().equals(currentNode)) {
                    Node neighbor = edge.getDestination();
                    double cost = edge.getCost();

                    if (!closed.contains(neighbor)) {
                        double tempG = currentNode.getG() + cost;

                        if (!open.contains(neighbor) || tempG < neighbor.getG()) {
                            neighbor.setParent(currentNode);
                            neighbor.setG(tempG);
                            neighbor.setH(heuristic(neighbor, endNode));
                            neighbor.setF(neighbor.getG() + neighbor.getH());

                            if (!open.contains(neighbor)) {
                                open.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
    }

    private double heuristic(Node node1, Node node2) {
        return Math.sqrt(Math.pow(node1.getX() - node2.getX(), 2) + Math.pow(node1.getY() - node2.getY(), 2));
    }

    public List<Node> getPath() {
        return path;
    }
}

class Node implements Comparable<Node> {
    private final int x;
    private final int y;
    private double f;
    private double g;
    private double h;
    private Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(f, other.f);
    }
}

class Edge {
    private final Node source;
    private final Node destination;
    private final double cost;

    public Edge(Node source, Node destination, double cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }

    public double getCost() {
        return cost;
    }
}
