package pszt.navigator;

import java.util.List;

public interface ProblemState {
	public double getEstimatedLength();
	public boolean isFinish();
	public List<ProblemState> extendStates();
	
	//TODO debug usun
	public void wypiszwierzcholki();
}
