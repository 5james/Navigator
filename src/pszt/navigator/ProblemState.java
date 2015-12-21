package pszt.navigator;

public interface ProblemState {
	public double getEstimatedLength();
	public boolean isFinish(Object finish);
	
	//TODO debug usun
	public void wypiszwierzcholki();
}
