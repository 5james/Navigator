package pszt.navigator;

import java.util.ArrayList;
import java.util.List;

public class AStar {
	List<ProblemState> states = new ArrayList<ProblemState>();
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
			states.addAll(next.extendStates());
			states.remove(next);
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
			else
				System.out.println("Mieli " + steps + "      " + states.size());
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
