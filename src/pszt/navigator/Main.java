package pszt.navigator;


/**
 * Created by Marcin on 2015-12-19.
 */
public class Main
{

	public static void main(String[] args) {
		Graph g = new Graph(100, 0.4);
		g.wypiszpunkty();
		AStar a = new AStar(g);
		a.solve();
	}
}
