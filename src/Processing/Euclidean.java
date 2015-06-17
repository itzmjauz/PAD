package Processing;

public class Euclidean implements DistanceMeasure {

	public double calculateDistance(Unit unit1, Unit unit2) {
		
		double distanceSum = 0.0;
		
		for(int x = 0 ; x < unit1.numberRow.numberRow.length ; x++) {
			double substraction = unit1.getNumber(x) - unit2.getNumber(x);
			double squared      = substraction * substraction;
			distanceSum += squared;
		}
		
		return Math.sqrt(distanceSum);
	}
}
