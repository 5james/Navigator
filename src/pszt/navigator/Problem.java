package pszt.navigator;

interface Problem
{
	void init();

	Object h(Object o);

	boolean isExnedable();

	void extendStates();

	void solve();
}