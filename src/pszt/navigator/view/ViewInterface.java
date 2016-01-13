package pszt.navigator.view;

import pszt.navigator.Node;

import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Created by Marcin on 2015-12-28.
 */
public interface ViewInterface
{
    double getRateValue();
    int getNumberOfNodes();
    void displayStringOnLogPanel(String s);

    void loadSolution(Vector<Node> solution);
}
