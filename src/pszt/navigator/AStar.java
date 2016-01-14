package pszt.navigator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AStar {
	List<ProblemState> states = new ArrayList<ProblemState>();
	List<ProblemState> closedStates = new ArrayList<ProblemState>();
	ProblemState next;
	int steps = 0;
	
	public AStar(ProblemState tempState)
	{
		states.add(tempState);
		next = tempState;
	}

	//Marcin 2015-12-30
	//bezparametrowy konstruktor, uzywany w kontrolerze
	//z tego co pamietam chcemy zmienic AStar by nie uzywal obiektu Problemu (w domysle grafu), wiec konstruktor jak znalazl ;)
	public AStar()
	{

	}

	public ProblemState solve()
	{
		boolean pathFound = false;
		while (!pathFound)
		{
			/* temp
			 * states.addAll(next.extendStates());
			 * temp*/
			
			//lista wszystkich nowych stanów
			List<ProblemState> newStates = new ArrayList<ProblemState>();
			newStates = next.extendStates();

			//dla każdego takiego stanu sprawdzamy, czy warto go dodawać
			Iterator<ProblemState> i = newStates.iterator();
			while (i.hasNext())
			{
				ProblemState newState = i.next();
				
			    for (ProblemState closedState : closedStates)
			    {
			    	//jeśli mają wspólny wierzchołek, ale jakość nowego stanu jest gorsza
			    	if (newState.isEqual(closedState) && newState.getEstimatedLength() > closedState.getEstimatedLength())
			    	{
			    		//usuwamy nowy stan z listy do dodania
			    		i.remove();
			    	}			    	
			    }

			    for (ProblemState openState : states)
			    {
			    	//analogicznie dla listy stanów otwartych, jeśli mają wspólny wierzchołek ale nowy jest gorszy
			    	if (newState.isEqual(openState) && newState.getEstimatedLength() > openState.getEstimatedLength())
			    	{
			    		//to usuwamy z listy do dodania
			    		i.remove();
			    	}
			    }
			    /**
			     *  @todo można potem sprawdzić, czy zamienić kolejność sprawdzania, które najpierw, co korzystniejsze
			     */
			}
			
			states.addAll(newStates);
			
			//przenosimy rozpatrzony stan do closedStates
			states.remove(next);
			closedStates.add(next);
			
			steps++;
			
			int found = findMinimum();
			if (found == -1)
			{
				System.out.println("CANNOT MAKE IT");
				return null;
			}
			else
			{
				next = states.get(found);
			}
			
			if (next.isFinish())
			{
				next.wypiszwierzcholki();
				System.out.println("FOUND PATH IN " + steps + " STEPS");
				System.out.println("LENGTH OF THAT PATH = " + next.getEstimatedLength());
				pathFound = true;
			}
			/*
			else
				System.out.println("Mieli " + steps + "      " + states.size());
			*/
		}
		if (pathFound == false)
		{
			next = null;
		}
		return next;
	}
	
	private int findMinimum()
	{
		double found = Double.MAX_VALUE;
		int indexFound = -1;
		for (int i = 0; i < states.size(); i++)
		{
			double temp = states.get(i).getEstimatedLength();
			if (temp < found)
			{
				found = temp;
				indexFound = i;
			}
		}
		return indexFound;
	}
	
	public String getSuccessLogs()
	{
		String toReturn = null;
		toReturn = "FOUND PATH IN " + steps + " STEPS\n";
		toReturn += "LENGTH OF THAT PATH = " + next.getEstimatedLength() + "\n";
		return toReturn;
	}
}
