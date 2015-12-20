package pszt.navigator;

import java.awt.geom.Point2D;
import java.util.Vector;

public class Graph implements Problem {

	class Node {
		Point2D.Double point;
		Vector<Node> neighbours = new Vector<Node>();
	}

	Node starter;
	Node finish;
	Vector<Node> nodes = new Vector<Node>();
	
	Graph(int amountOfPoints, double rate)
	{
		createNodes(amountOfPoints);
		
		double x = Math.random();
		createNeighbourhood(rate, x);
	}
	
	private void createNeighbourhood(double rate, double x)
	{
		for (int i=0; i < nodes.size()-1; i++)
		{
			for (int j=i+1; j < nodes.size(); j++)
			{
				Point2D.Double p1 = nodes.elementAt(i).point;
				Point2D.Double p2 = nodes.elementAt(j).point;
				double exp = Math.exp(-(p1.distance(p2))/rate);
				if (x<exp)
				{
					connectNeighbours (i, j);
				}
			}
		}
	}
	
	private void connectNeighbours(int i, int j)
	{
		Node n1 = nodes.elementAt(i);
		Node n2 = nodes.elementAt(j);
		n1.neighbours.add(n2);
		n2.neighbours.add(n1);
	}
	
	private void createNodes(int amountOfPoints)
	{
		for (int i=0; i < amountOfPoints; i++)
		{
			Node newNode = new Node();
			newNode.point = generateNewPoint();
			nodes.add(newNode);
		}
	}
	
	private Point2D.Double generateNewPoint()
	{
		Point2D.Double pnt;
		do
		{
			double x = Math.random();
			double y = Math.random();
			pnt = new Point2D.Double();
			pnt.setLocation(x, y);
		} while (!isPointUnique(pnt));
		
		return pnt;
		
	}
	
	private boolean isPointUnique(Point2D.Double pnt)
	{
		for (int i = 0; i < nodes.size(); i++)
		{
			if (pnt.equals(nodes.get(i).point))
			{
				return false;
			}
		}
		return true;
	}
	
	
	public void wypiszpunkty()
	{
		int size = 0;
		for (int i = 0; i < nodes.size(); i++)
		{
			System.out.println(nodes.elementAt(i).point.toString());
			System.out.println(nodes.elementAt(i).neighbours.size());
			size += nodes.elementAt(i).neighbours.size();
		}
		System.out.println(size);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object h(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void solve() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isExnedable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void extendStates() {
		// TODO Auto-generated method stub

	}

	//mojazmiana

}
