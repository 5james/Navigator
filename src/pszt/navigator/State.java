package pszt.navigator;

import java.awt.geom.Point2D;
import java.util.Vector;

public class State implements ProblemState {
	Vector<Node> path = new Vector<>();
	public double distanceTraveled = 0;

	@Override
	public double getEstimatedLength() {
		double estimatedLength = distanceTraveled;
		estimatedLength += path.lastElement().h;
		return estimatedLength;
	}

	@Override
	public boolean isFinish(Object finish) {
		Node nodeFinish = (Node) finish;
		Node pathFinish = path.lastElement();
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
