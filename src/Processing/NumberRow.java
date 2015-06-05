package Processing;

public class NumberRow {
	
	private double[] numberRow;
	private int size;
	
	NumberRow(int maximumSize) {
		numberRow = new double[maximumSize];
	}
	
	void add(double entry) {
		numberRow[size] = entry;
		size++;
	}
	
	double get(int index) {
		return numberRow[index];
	}
}
