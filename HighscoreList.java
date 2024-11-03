import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class HighscoreList {
	private DefaultListModel<Object> dlm;
	private int count = 0;
	private int size = 0;

	public HighscoreList() {
		dlm = new DefaultListModel<Object>();
	}

	public void add(int anElement) {
		boolean hasswapped = false;
		int temp;
		if (count == 0) { // vid första insättning adderas den på index 0
			String tmp = getInitials();
			dlm.add(0, tmp + " " + anElement);
			count++;
			size++;
		} else {
			for (int i = 0; i < dlm.getSize(); i++) { // går igenom listan kollar om den är >= isf läggs score in där
				if (anElement >= returnElement(i)) {
					temp = i; //sparar indexet på där elementet ska läggas in
					String tmp = getInitials(); //funktionen getInitials för att få 3 initaler från användaren
					dlm.add(temp, tmp + " " + anElement); //lägger ihop initaler med poäng
					if (size > Const.LIST_SIZE - 1) { // kollar om listan är störe än 10 isåfall tas den sista elementet i listan bort
						remove(Const.LIST_SIZE);
					}
					if (size > Const.LIST_SIZE - 1) {
						size = Const.LIST_SIZE - 1;
					}
					size++;
					hasswapped = true;
					break;
				}
				if (hasswapped == false && size < Const.LIST_SIZE && i == dlm.getSize() - 1) { // går igenom listan
																								// kollar om den är <
																								// isf läggs score in
																								// där
					String tmp = getInitials();
					dlm.add(dlm.getSize(), tmp + " " + anElement);
					if (size > Const.LIST_SIZE - 1) {
						remove(Const.LIST_SIZE);
					}
					if (size > Const.LIST_SIZE) {
						size = Const.LIST_SIZE;
					}
					size++;
					hasswapped = true;
					break;
				}
				hasswapped = false;
			}
		}
	}

	public DefaultListModel<Object> getList() { // returnerar hela listan för att kunna visas på skärmen
		return dlm;
	}

	public void remove(int index) {
		dlm.remove(index);
	}

	public int returnElement(int i) { // parsar strängen som en int och tar bort initialerna
		String string = (String) dlm.get(i);
		string = string.substring(Const.MAX_INPUT + 1);
		return Integer.parseInt(string);
	}

	public String getInitials() { // funktion för att få initaler från användaren ser till att längden på strängen
									// alltid är 3
		String tmp = JOptionPane.showInputDialog("Mata in dina initaler (max " + Const.MAX_INPUT + ")");
		if (tmp == null || tmp.length() == 0) {
			return "N/A";
		} else if (tmp.length() > Const.MAX_INPUT) {
			tmp = tmp.substring(0, Const.MAX_INPUT);
			return tmp;
		} else if (tmp.length() == 1) {
			tmp = tmp + " " + " ";
			return tmp;
		} else if (tmp.length() == Const.MAX_INPUT - 1) {
			tmp = tmp + " ";
			return tmp;
		}
		return tmp;
	}
}
