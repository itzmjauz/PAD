package Processing;

public class UnitRow {
	private Unit[] unitRow;
	private int size = 0;
	
	UnitRow(int maximumSize) {
		unitRow = new Unit[maximumSize];
	}
	
	public void add(Unit unit){
		unitRow[size] = unit;
		size++;
	}
	
	public Unit get(int index){
		return unitRow[index];
	}
	
	public int size() {
		return size;
	}
	
	public void normalize(int variables){
		for(int row = 0 ; row < variables ; row++) {
			double max = maximum(row);
			double min = minimum(row);
			
			for(int unitNum = 0 ; unitNum < size ; unitNum++) {
				double result = normal(max, min, unitRow[unitNum].getNumber(row));
				
				unitRow[unitNum].numberRow.numberRow[row] = result; 
				
			}
		}
	}
	
	private double normal(double max, double min, double element) {
		double dividend = element - min;
		double divisor = max - min;
		
		return dividend / divisor;
	}
	
	private double maximum(int row) {
		double maximum = 0;
		
		for(int x = 0 ; x < size ; x++) {
			double value = get(x).getNumber(row);
			if(value > maximum) {
				maximum = value;
			}
		}
		
		return maximum;
	}
	
	private double minimum(int row) {
		double minimum = get(0).getNumber(row);
		
		for(int x = 0 ; x < size ; x++) {
			double value = get(x).getNumber(row);
			if(value < minimum) {
				minimum = value;
			}
		}
		
		return minimum;
	}
	
	public double average(int row) {
		double sum = 0.0;
		
		for(int x = 0 ; x < size ; x++) {
			sum += get(x).getNumber(row);
		}
		
		return sum/size;
	}
}
