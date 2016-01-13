package pszt.navigator;
import pszt.navigator.controller.Controller;
import pszt.navigator.view.View;
import java.awt.*;

/**
 * Created by Marcin on 2015-12-19.
 */

//struktura podobna do mvc
public class Main
{

	public static void main(String[] args) {
		Graph g = new Graph(100, 0.1);
		g.wypiszpunkty();
		State first = (State) g.init();
		AStar a = new AStar(first);
		a.solve();
	}
}
//		EventQueue.invokeLater(
//				new Runnable() {
//					@Override
//					public void run()
//					{
//						Controller controller = new Controller();
//						View view = new View(controller);
//						controller.setView(view);
//					}
//				}
//		)
