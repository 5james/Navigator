package pszt.navigator;

import java.util.ArrayList;
import java.util.List;

public class AStar {
	List<ProblemState> states = new ArrayList<ProblemState>();
	ProblemState next;
	private Problem problem;
	int steps = 0;
	
	AStar(Problem p)
	{
		problem=p;
		ProblemState tempState = p.init();
		states.add(tempState);
		next = tempState;
	}
	
	void solve()
	{
		boolean pathFound = false;
		while (!pathFound)
		{
			states.addAll(problem.extendStates(next));
			states.remove(next);
			steps++;
			
			int found = findMinimum();
			if (found == -1)
			{
				System.out.println("CANNOT MAKE IT");
				return;
			}
			else
			{
				next = states.get(found);
			}
			
			if (next.isFinish(problem.getFinish()))
			{
				next.wypiszwierzcholki();
				System.out.println("FOUND PATH IN " + steps + " STEPS");
				pathFound = true;
			}
			else
				System.out.println("Mieli " + steps);
		}
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
}
