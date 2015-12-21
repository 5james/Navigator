package pszt.navigator;

import java.util.List;

interface Problem
{
	public List<ProblemState> extendStates(ProblemState p);
	
	public ProblemState init();
	
	public Object getFinish();
}