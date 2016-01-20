package pszt.navigator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class State implements ProblemState {
	Vector<Node> path = new Vector<>();
	public double distanceTraveled = 0;
	Graph graph;
	
	State (Graph g)
	{
		graph = g;
	}

	@Override
	public double getEstimatedLength()
	{
		double estimatedLength = distanceTraveled;
		estimatedLength += path.lastElement().h;
		return estimatedLength;
	}

	@Override
	public boolean isFinish()
	{
		Node nodeFinish = graph.getFinish();
		Node pathFinish = path.lastElement();
		if (pathFinish.equals(nodeFinish))
			return true;
		return false;
	}
	
	public void getSolution()
	{
		for (int i = 0; i < path.size(); i++)
		{
			Point2D.Double pt = path.elementAt(i).point;
			System.out.println(pt);
		}
	}

	@Override
	public List<ProblemState> extendStates()
	{
		List<ProblemState> tempList= new ArrayList<ProblemState>();
		Node back = path.lastElement();
		for (int i = 0; i < back.neighbours.size(); i++)
		{
			State s = new State(graph);
			copyPath(s);
			
			if (!stateHasNode(s, back.neighbours.elementAt(i)))
			{
				s.path.add(back.neighbours.elementAt(i));
				double tempDistance = back.point.distance(back.neighbours.elementAt(i).point);
				s.distanceTraveled += tempDistance;
				tempList.add(s);
			}
		}
		
		return tempList;
	}
	
	@Override
	public boolean isEqual(ProblemState other)
	{
		return hasSameEnd((State) other);
	}
	
	/**
	 * sprawdza, czy stany mają taki sam wierzchołek końcowy
	 * wykorzystywana do zaimplementowania isEqual()
	 * 
	 * @param other - drugi stan z którym porównujemy
	 * @return true, jeśli takie same
	 */
	private boolean hasSameEnd(State other)
	{
		return this.path.lastElement().equals(other.path.lastElement());
	}
	
	
	private boolean stateHasNode(State s, Node node)
	{
		for (int i = 0; i < s.path.size(); i++)
		{
			if (s.path.elementAt(i).equals(node))
			{
				//System.out.println("--------------------------------------------------------------------");
				return true;
			}
				
		}
		return false;
	}
	
	private void copyPath(State to)
	{
		for (int i = 0; i < path.size(); i++)
		{
			to.path.addElement(path.elementAt(i));
		}
	}
	
	public Vector<Node> getPath()
	{
		return path;
	}

	public void setPath(Vector<Node> path)
	{
		this.path = path;
	}
}
