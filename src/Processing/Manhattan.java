package Processing;

public class Manhattan implements DistanceMeasure {
	
	public double calculateDistance(Unit unit1, Unit unit2) {
		
		double distanceSum = 0.0;
		
		for(int x = 0 ; x < unit1.getRow().numberRow.length ; x++) {
			double result = unit1.getNumber(x) - unit2.getNumber(x);
			distanceSum += Math.abs(result);
		}
		
		return distanceSum;
	}
}
