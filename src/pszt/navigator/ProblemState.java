package pszt.navigator;

import java.util.List;

public interface ProblemState {
	public double getEstimatedLength();
	public boolean isFinish();
	public List<ProblemState> extendStates();

	/**
	 * sprawdza czy stany są równe w sensie brzegowym (?)
	 * @param stan z którym porównujemy
	 * @return true, jeśl równe
	 */
	public boolean isEqual(ProblemState other);
	
	//TODO debug usun
	public void getSolution();
}
