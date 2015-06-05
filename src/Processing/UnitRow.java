package Processing;

public class UnitRow {
	private Unit[] unitRow;
	private int size = 0;;
	
	UnitRow(int maximumSize) {
		unitRow = new Unit[maximumSize];
	}
	
	void add(Unit unit){
		unitRow[size] = unit;
		size++;
	}
	
	Unit get(int index){
		return unitRow[index];
	}
}
