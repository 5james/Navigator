package pszt.navigator.view;

import pszt.navigator.Graph;
import pszt.navigator.Node;
import pszt.navigator.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.Vector;


/**
 * Created by Marcin on 2015-12-20.
 */
public class View implements ViewInterface
{
    private static final int DEFAULT_WIDTH = 960;
    private static final int DEFAULT_HEIGHT = 800;

    private Controller controller;
    private Graph graph;

    private JFrame frame;
    private MenuBar menuBar;

    //panele
    private JPanel graphPanelWrapper;       //'opakowanie' dla panelu z grafem, ułatwia organizację i rysowanie
    private GraphPanel graphPanel;          //panel, na którym rysowany jest graf
    private JPanel rightPanel;              //'opakowanie' dla paneli Log i Settings
    private LogPanel logPanel;              //panel do wyświetlania wiadomości
    private SettingsPanel settingsPanel;    //panel z przyciskami i polami do uzupełniania danych

    public View(Controller controller)
    {

        this.controller = controller;

        //ustawienia dla okna
        frame = new JFrame("Navigator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        frame.setLayout(new BorderLayout());

        //panel grafu
        graphPanel = new GraphPanel(graph, controller);

        //wrapper dla panelu z grafem - możliwość dodania obramowania + bialego koloru
        graphPanelWrapper = new JPanel(new BorderLayout());
        graphPanelWrapper.add(graphPanel, BorderLayout.CENTER);
        graphPanelWrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Graph"),
                BorderFactory.createEmptyBorder(5,5,5,5)));

        //panel ustawień
        settingsPanel = new SettingsPanel(controller);

        //panel logów
        logPanel = new LogPanel();

        //wrapper dla paneli ustawien i logow
        rightPanel = new JPanel(new GridLayout(2,1));

        //dodanie paneli do prawego panelu
        rightPanel.add(settingsPanel);
        rightPanel.add(logPanel);

        //pasek menu
        menuBar = new MenuBar();

        //dodanie paneli do głównego okna
        frame.add(graphPanelWrapper,BorderLayout.CENTER);
        frame.add(rightPanel,BorderLayout.EAST);
        frame.setJMenuBar(menuBar);

        //końcowe ustawienia dla okna
        //frame.setResizable(false);
        frame.pack();
        frame.setLocation(100, 100);
        frame.setVisible(true);
    }

    public void setGraph(Graph graph)
    {
        this.graph = graph;
        graphPanel.setGraph(graph);
        graphPanel.resetStartEndColorPoints();  //reset kolorów dla punktów
    }

    public void updateView()
    {
        graphPanel.repaint();
    }

    @Override
    public double getRateValue()
    {
        // TODO: 2015-12-31  
        return 0;
    }

    @Override
    public int getNumberOfNodes()
    {
        // TODO: 2015-12-31  
        return 0;
    }

    @Override
    public void displayStringOnLogPanel(String s)
    {
        logPanel.displayString(s);
    }

    @Override
    public void loadSolution(Vector<Node> solution)
    {
        graphPanel.createSolutionShapes(solution);
    }
}

