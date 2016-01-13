package pszt.navigator;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Graph implements Problem {


//Marcin 2015-12-29
//moved outsite to make this class public
//	class Node {
//		Point2D.Double point;
//		Vector<Node> neighbours = new Vector<Node>();
//		double h;
//	}

	private Node starter = null;
	private Node finish = null;
	private Vector<Node> nodes = new Vector<Node>();

	public Graph(int amountOfPoints, double rate)
	{
		createNodes(amountOfPoints);
		
		//
		//System.out.println(x);
		createNeighbourhood(rate);

		//TODO debug usun
		System.out.println("Rate = " + rate);
		
		//TODO usun
		
		setStarter(nodes.elementAt(0));
		setFinish(nodes.elementAt(nodes.size()-1));
	}

	public void computeH()
	{
		if (finish != null)
		{
			
			for (int i = 0; i < nodes.size(); i++)
			{
				Point2D.Double pt = nodes.elementAt(i).point;
				nodes.elementAt(i).h = finish.point.distance(pt);
			}
		}
	}

	private void createNeighbourhood(double rate)
	{
		for (int i=0; i < nodes.size()-1; i++)
		{
			for (int j=i+1; j < nodes.size(); j++)
			{
				Point2D.Double p1 = nodes.elementAt(i).point;
				Point2D.Double p2 = nodes.elementAt(j).point;
				double exp = Math.exp(-(p1.distance(p2))/rate);
				double x = Math.random();
				
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
	public ProblemState init() {
		State s = new State(this);
		s.path.addElement(starter);
		return s;
	}
	
	public Node getStarter() {
		return starter;
	}

	public void setStarter(Node starter) {
		this.starter = starter;
	}

	public Node getFinish() {
		return finish;
	}

	public void setFinish(Node finish) {
		this.finish = finish;
		this.computeH();
	}

	//Marcin 2015-12-29
	public Vector<Node> getNodes()
	{
		return nodes;
	}

	public Node getNodeAtPoint(Point2D.Double point)
	{
		for(Node n : nodes)
		{
			if(n.getPoint().equals(point))
				return n;
		}
		return null;
	}
}

