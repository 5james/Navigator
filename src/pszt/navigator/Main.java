package pszt.navigator;
import pszt.navigator.controller.Controller;
import pszt.navigator.view.View;
import java.awt.*;

/**
 * Basics of Artificial Intelligence - Navigator
 * @authors: Jakub Guzek, Kacper Stachyra, Marcin Jarząbek
 * @tutor: Paweł Wawrzyński, PhD
 * EiTI 2015
 */


/**
 * Klasa zawierająca metodę <code>main()</code>
 * W projekcie wykorzystano wzorzec MVC
 * Model stanowi implementacja algorytmu A*, oraz graf miast służący do zaprezentowania działanie algorytmu
 * (znalezienie drogi między wyznaczonymi miastami)
 */
public class Main
{

	public static void main(String[] args) {
		EventQueue.invokeLater(
				new Runnable() {
					@Override
					public void run()
					{
						Controller controller = new Controller();
						View view = new View(controller);
						controller.setView(view);
					}
				}
		);
	}
}

