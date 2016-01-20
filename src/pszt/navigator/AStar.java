package pszt.navigator;

import java.util.ArrayList;
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

	public ProblemState solve()
	{
		boolean pathFound = false;
		while (!pathFound)
		{
			//lista wszystkich nowych stanów
			List<ProblemState> newStates = new ArrayList<ProblemState>();
			newStates = next.extendStates();
			
			//lista stanów do usunięcia
			List<ProblemState> toDeleteStates = new ArrayList<ProblemState>();
			
			//dla każdego takiego stanu sprawdzamy, czy warto go dodawać
			for (ProblemState newState : newStates)
			{
			    for (ProblemState closedState : closedStates)
			    {  	
			    	//jeśli mają wspólny wierzchołek, ale jakość nowego stanu jest gorsza
			    	if (newState.isEqual(closedState) && newState.getEstimatedLength() > closedState.getEstimatedLength())
			    	{
			    		//stan do usunięcia przed dodaniem
			    		toDeleteStates.add(newState);
			    	}			    	
			    }
			    for (ProblemState openState : states)
			    {
			    	//analogicznie dla listy stanów otwartych, jeśli mają wspólny wierzchołek ale nowy jest gorszy
			    	if (newState.isEqual(openState) && newState.getEstimatedLength() > openState.getEstimatedLength())
			    	{
			    		//to do usunięcia
			    		toDeleteStates.add(newState);
			    	}   	
			    }
			}			
			//usuwamy stany do usunięcia przed dodaniem
			newStates.removeAll(toDeleteStates);
			
			//dodajemy nowe stany do openStates
			states.addAll(newStates);
			
			//przenosimy rozpatrzony stan do closedStates
			states.remove(next);
			closedStates.add(next);
			
			steps++;
			
			int found = findMinimum();
			if (found == -1)
			{
				/*System.out.println("CANNOT MAKE IT");*/
				return null;
			}
			else
			{
				next = states.get(found);
			}
			
			if (next.isFinish())
			{
				next.getSolution();
				System.out.println("FOUND PATH IN " + steps + " STEPS");
				System.out.println("LENGTH OF THAT PATH = " + next.getEstimatedLength());
				pathFound = true;
			}

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
		String toReturn = "FOUND PATH IN " + steps + " STEPS\n";
		toReturn += "LENGTH OF THAT PATH = " + next.getEstimatedLength() + "\n";
		return toReturn;
	}
}
