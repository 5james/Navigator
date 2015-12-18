public interface Problem {
	void init();

	Object h(Object o);

	boolean exnedable();

	void extendStates();

	void solve();
}