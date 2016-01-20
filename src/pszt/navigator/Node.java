package pszt.navigator;

import java.awt.geom.Point2D;
import java.util.Vector;

/**
 * Created by Marcin on 2015-12-29.
 */
public class Node
{
	//Marcin 2015-12-29
	//zostawione bez modyfikatora private - ze względu na kod w klasie Graph, ale mocno wskazane by to zmienić
	Point2D.Double point;
	Vector<Node> neighbours = new Vector<Node>();
	double h;

	
	public boolean equals(Node n)
	{
		if (point.x == n.point.x && point.y == n.point.y)
			return true;
		return false;
	}

	public Point2D.Double getPoint()
	{
		return point;
	}

	public void setPoint(Point2D.Double point)
	{
		this.point = point;
	}

	public Vector<Node> getNeighbours()
	{
		return neighbours;
	}

	public void setNeighbours(Vector<Node> neighbours)
	{
		this.neighbours = neighbours;
	}

	public double getH()
	{
		return h;
	}

	public void setH(double h)
	{
		this.h = h;
	}
}
