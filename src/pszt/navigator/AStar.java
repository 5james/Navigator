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
		states.addAll(problem.extendStates(next));
		states.remove(next);
		steps++;
		
	}
}
