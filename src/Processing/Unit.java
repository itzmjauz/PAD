package Processing;

public class Unit {
	private NumberRow numberRow;
	String name;
	
	Unit(String newName, int size) {
		name = newName;
		numberRow = new NumberRow(size);
	}
	
	void addNumber(double number) {
		numberRow.add(number);
	}
	
	NumberRow getRow() {
		return numberRow;
	}
	
	double getNumber(int index) {
		return numberRow.get(index);
	}
	
}
