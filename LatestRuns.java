import javax.swing.DefaultListModel;

public class LatestRuns {
	private DefaultListModel<Object> dlm;

	public LatestRuns() {
		dlm = new DefaultListModel<Object>();
	}

	public void add(Object anElement) { //lägger alltid till index 0
		dlm.add(0, anElement);
	}

	public void remove(int index) { //kallas från Fonster.java när index är > 2
		dlm.remove(index);
	}

	public DefaultListModel<Object> getList() {// returnerar hela listan
		return dlm;
	}
}
