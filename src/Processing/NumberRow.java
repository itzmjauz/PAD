package Processing;

public class NumberRow {
	double[] numberRow;
	private int size;
	
	NumberRow(int maximumSize) {
		numberRow = new double[maximumSize];
	}
	
	public void add(double entry) {
		numberRow[size] = entry;
		size++;
	}
	
	public double get(int index) {
		return numberRow[index];
	}
}
