package Processing;

import java.util.Arrays;

public class Dataset {
	int clusters, elements, variables, type;
	public String[] variableNames;
	private UnitRow unitRow;
	
	Dataset(int clusters, int elements, int variables, String[] variableNames) {
		this.clusters = clusters;
		this.elements = elements;
		this.variables = variables;
		this.variableNames = variableNames;
		this.unitRow = new UnitRow(elements);
		process(); // move the type ( first row name ) to its own constant. set variableNames to containt the rest
	}
	
	private void process() {
		String[] names = new String[variables];
		
		for(int x = 0 ; x < variables ; x++) {
			names[x] = variableNames[x+1];
		}
		
		variableNames = names;
	}
	
	public void addUnit(Unit unit) {
		unitRow.add(unit);
	}
	
	public Unit getUnit(int index) {
		return unitRow.get(index);
	}
	
	public void normalize() {
		unitRow.normalize(variables);
	}
	
	public void preselect() {
		// we need a way to get names after sorting through an array.
		// need to bind names to the deviation value, then sort the array and get the first 50 
		// keep in mind that variableNames also contains the type of the first row ( which is names )
		if(variables <= 50) return;
		
		double borderValue = getBorderValue();  
		String[] names = new String[50]; // contains used names
		int[] indexes = new int[50];     // contains the used indexes so that we can alter our unitrow
		int index = 0;                   // current position in names/indexes list
		
		for(int x = 0 ; x < variables ; x++) { // now compare all elements+ their results.
			if(getDeviation(x) > borderValue) {
				names[index]   = variableNames[x];
				indexes[index] = x;
				index++;
			}
		}
		variableNames = names;
		updateUnits(indexes);
	}
	
	private double getBorderValue() { // used to determine what the lowest value in our preselection is,
									  // this way we can filter by grabbing anything bigger than the borderValue
		double[] results = new double[variables];
		
		for(int x = 0 ; x < variables ; x++) { // for each row
			results[x] = getDeviation(x);      // generate  all results
		}
		Arrays.sort(results); // sort it
		
		return results[results.length - 51]; // we want the -50th index ( so length - 51 )
	}
	
	
	private double getDeviation(int row) {
		return Math.sqrt(getVariance(row)); 
	}
	
	private double getVariance(int row) {
		double mean = average(row);
        double sum = 0.0;
        for(int x = 0 ; x < elements ; x++){
        	double value =  unitRow.get(x).getNumber(row);
            sum += (mean-value)*(mean-value);
        }
        double cordivisor = elements - 1;
        double correction = 1 / cordivisor; // 1 / n - 1
        return correction * sum;
    }
	 
	
	private double average(int row) {
		return unitRow.average(row);
	}
	
	private void updateUnits(int[] indexes) { // we update all units to only contain our preselected values
		UnitRow unitRow = new UnitRow(elements);
		
		for(int x = 0 ; x < elements ; x++) { // for each unit
			Unit originalUnit = this.unitRow.get(x);
			Unit unit = new Unit(originalUnit.name, 50);
			
			for(int y = 0 ; y < indexes.length ; y++) { // for each variable within a given unit
				unit.addNumber(originalUnit.getNumber(indexes[y]));
			}
			unitRow.add(unit);
		}
		
		this.unitRow = unitRow;
		variables = indexes.length;
	}
}
