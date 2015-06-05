package Processing;

public class Dataset {
	int clusters, elements, variables;
	String[] variableNames;
	private UnitRow unitRow;
	
	Dataset(int newClusters, int newElements, int newVariables, String[] newVariableNames) {
		clusters = newClusters;
		elements = newElements;
		variables = newVariables;
		variableNames = newVariableNames;
		unitRow = new UnitRow(elements);
	}
	
	void addUnit(Unit unit) {
		unitRow.add(unit);
	}
	
	Unit getUnit(int index) {
		return unitRow.get(index);
	}
}
