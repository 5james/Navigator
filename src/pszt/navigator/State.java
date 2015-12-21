package pszt.navigator;

import java.awt.geom.Point2D;
import java.util.Vector;

public class State implements ProblemState {
	Vector<Graph.Node> path = new Vector<Graph.Node>();
	public double distanceTraveled;

	@Override
	public double getEstimatedLength() {
		double estimatedLength = distanceTraveled;
		estimatedLength += path.lastElement().h;
		return estimatedLength;
	}

	@Override
	public boolean isFinish(Object finish) {
		Graph.Node nodeFinish = (Graph.Node) finish;
		Graph.Node pathFinish = path.lastElement();
		if (pathFinish.equals(nodeFinish))
			return true;
		return false;
	}
	
	public void wypiszwierzcholki()
	{
		for (int i = 0; i < path.size(); i++)
		{
			Point2D.Double pt = path.elementAt(i).point;
			System.out.println(pt);
		}
	}
}
