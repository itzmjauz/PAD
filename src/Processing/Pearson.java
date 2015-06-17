package Processing;

public class Pearson implements DistanceMeasure {

	public double calculateDistance(Unit unit1, Unit unit2) {
		double[] numbers1 = getNumbers(unit1);
		double[] numbers2 = getNumbers(unit2);
		double average1   = average(numbers1);
		double average2   = average(numbers2);
		double derivation1= derivation(numbers1);
		double derivation2= derivation(numbers2);
		double sum = 0.0;
		
		for(int x = 0 ; x < numbers1.length ; x++) {
			double substraction  = numbers1[x] - average1;
			double leftdivision  = substraction / derivation1;
			substraction = numbers2[x] - average2;
			double rightdivision = substraction / derivation2;
			
			double multiplication = leftdivision * rightdivision;
			sum += multiplication;
		}
		double divisor = numbers1.length - 1;
		double result  = sum / divisor;
		
		return (1 - result);
	}
	
	private double average(double[] numbers) {
		double sum = 0.0;
		
		for(int x = 0 ; x < numbers.length ; x++) {
			sum += numbers[x];
		}
		
		double result =  sum / numbers.length;
		return result;
	}
	
	private double derivation(double[] numbers) {
		double sum = 0.0;
		double average = average(numbers);
		
		for(int x = 0 ; x < numbers.length ; x++) {
			double substraction = numbers[x] - average;
			double squared      = substraction * substraction;
			sum += squared;
		}
		
		double divisor = numbers.length - 1;
		double division = sum / divisor;
		
		return Math.sqrt(division);
	}
	
	private double[] getNumbers(Unit unit) {
		return unit.numberRow.numberRow;
	}
}
