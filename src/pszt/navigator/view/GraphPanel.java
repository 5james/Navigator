package pszt.navigator.view;

import pszt.navigator.Graph;
import pszt.navigator.Node;
import pszt.navigator.controller.Controller;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Marcin on 2015-12-28.
 */
public class GraphPanel extends JPanel
{
    public static int graphPanelPreferredWidth = 800;
    public static int graphPanelPreferredHeight = 800;

    public static int graphPanelWidthRange = 790;
    public static int graphPanelHeightRange = 790;

    public static int nodeSize = 10;

    private Controller controller;
    private Graph graph;
    private ArrayList<Shape> shapes;    //rysowane nody
    private ArrayList<Shape> edges;     //krawedzie
    private ArrayList<Shape> solution;  //i rozwiązanie

    private ArrayList<Point2D.Double> nodesCoordinates; //współrzędne node'ów (w układzie o osiach [0..1]

    private int greenStartPoint, redEndPoint, highlightedPoint; //indeksy (start,end,highlited) w shapes - potrzebne do malowania na właściwy kolor

    public GraphPanel(Graph graph, Controller controller)
    {
        this.controller = controller;
        this.graph = graph;
        shapes = new ArrayList<Shape>();
        this.addMouseListener(createMouseListener());
        this.setBackground(Color.WHITE);
        greenStartPoint = redEndPoint = highlightedPoint = -1;
        solution = null;
    }

    public void setGraph(Graph graph)
    {
        this.graph = graph;
        solution = null;
    }

    public void createNodes()
    {
        if(graph != null)
        {
            shapes = new ArrayList<Shape>(); //nowa lista node'ów
            nodesCoordinates = new ArrayList<Point2D.Double>(); //i lista współrzędnych odpowiadających node'om wyświetlanym
            Vector<Node> nodes =  graph.getNodes();
            for(Node n : nodes)
            {
                nodesCoordinates.add(n.getPoint());
                Point2D.Double translatedPoint = translateCoordinates(n.getPoint());
                Ellipse2D.Double node = new Ellipse2D.Double(translatedPoint.getX()-5, translatedPoint.getY()-5, nodeSize, nodeSize);
                shapes.add(node);
            }
        }
        else
        {
            System.out.println("At first, you have to generate graph");
        }
    }

    public void createLines()
    {
        if(graph != null)
        {
            edges = new ArrayList<>();
            Vector<Node> nodes =  graph.getNodes();
            Vector<Node> currentNodeNeighbours;
            for(Node n : nodes)
            {
                Point2D.Double translatedPointA = translateCoordinates(n.getPoint());
                currentNodeNeighbours = n.getNeighbours();
                for(Node nx : currentNodeNeighbours)
                {
                    Point2D.Double translatedPointB = translateCoordinates(nx.getPoint());
                    Line2D.Double line = new Line2D.Double(translatedPointA.getX(),translatedPointA.getY(),translatedPointB.getX(),translatedPointB.getY());
                    edges.add(line);
                }

            }
        }
        else
        {
            System.out.println("At first, you have to generate graph");
        }
    }

    public void createSolutionShapes(Vector<Node> sol)
    {
        // TODO: 2016-01-13 narysowanie rozwiązania
        solution = new ArrayList<>();
        if(sol != null) {
            for (int i = 0; i < sol.size(); i++) {
                Point2D.Double translatedPointA = translateCoordinates(sol.get(i).getPoint());
                Ellipse2D.Double node = new Ellipse2D.Double(translatedPointA.getX() - 7, translatedPointA.getY() - 7, nodeSize+2, nodeSize+2);
                solution.add(node);
                if (i < sol.size() - 1) {
                    Point2D.Double translatedPointB = translateCoordinates(sol.get(i+1).getPoint());
                    Line2D.Double line = new Line2D.Double(translatedPointA.getX(), translatedPointA.getY(), translatedPointB.getX(), translatedPointB.getY());
                    solution.add(line);
                }

            }
        }

    }

    //skalowanie - przejście ze wzpółrzędnych z zakresu [0..1] na współrzędne w pikselach
    private static Point2D.Double translateCoordinates(Point2D.Double point)
    {
        return new Point2D.Double(point.getX()*graphPanelWidthRange,point.getY()*graphPanelHeightRange);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        if(graph != null)
        {
            createNodes();
            createLines();
            for (Shape s : shapes)
            {
                if (s != null)
                {
                    g2.draw(s);
                    if(shapes.indexOf(s) == greenStartPoint) {
                        g2.setPaint(Color.GREEN);
                        g2.fill(s);
                    }
                    else
                        if (shapes.indexOf(s) == redEndPoint) {
                            g2.setPaint(Color.RED);
                            g2.fill(s);
                        }
                    else
                        if(shapes.indexOf(s) == highlightedPoint){
                            g2.setPaint(Color.YELLOW);
                            g2.fill(s);
                        }
                    else {
                        g2.setPaint(Color.BLACK);
                        g2.fill(s);
                    }
                }
            }
            for(Shape e : edges)
            {
                if(e!=null)
                {
                    g2.draw(e);
                }
            }
            if(solution != null) {
                for (Shape s : solution) {
                    g2.setPaint(Color.RED);
                    g2.setStroke(new BasicStroke(4));
                    if (s != null) {
                        g2.draw(s);
                        g2.fill(s);
                    }
                }
            }
        }
    }

    public MouseListener createMouseListener()
    {
        return new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                if(graph != null && (controller.getStartEndSwich() == 'S' || controller.getStartEndSwich() == 'E')) //button Start or End was clicked
                {
                    for (Shape s : shapes) //iteracja po wszystkich narysowanych kształtach i sprawdzenie, czy w jakiś kliknięto
                    {
                        if (s.contains(e.getPoint()))  //czy trafiono w node'a
                        {
                            int index = shapes.indexOf(s);

                            if(controller.getStartEndSwich() == 'S') {
                                controller.setStart(graph.getNodeAtPoint(nodesCoordinates.get(index)));
                                greenStartPoint = index;
                            }
                            else {
                                controller.setEnd(graph.getNodeAtPoint(nodesCoordinates.get(index)));
                                redEndPoint = index;
                            }

                            controller.resetStartEndSwich();
                            break;
                        }
                    }
                }
            }
        };
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(graphPanelPreferredWidth, graphPanelPreferredHeight);
    }

    public static void main(String[] args)
    {
        Point2D.Double point = new Point2D.Double(0.3123123,0.5123123);
        System.out.println("New coordinates:" + GraphPanel.translateCoordinates(point));
    }

    public void resetStartEndColorPoints()
    {
        greenStartPoint = redEndPoint = -1;
    }
}
