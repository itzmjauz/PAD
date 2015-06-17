package Processing;

public class Unit {
	public NumberRow numberRow;
	public String name;
	
	Unit(String newName, int size) {
		name = newName;
		numberRow = new NumberRow(size);
	}
	
	public void addNumber(double number) {
		numberRow.add(number);
	}
	
	public NumberRow getRow() {
		return numberRow;
	}
	
	public double getNumber(int index) {
		return numberRow.get(index);
	}
}
