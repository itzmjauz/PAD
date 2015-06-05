package Processing;

import java.util.Scanner;
import java.io.PrintStream;
import ui.UIAuxiliaryMethods;

public class Processing {
	
	PrintStream out;
	Dataset data;
	
	Processing() {
		out = new PrintStream(System.out);
		UIAuxiliaryMethods.askUserForInput();
	}
	
	void Start() {
		data = getDataset();
		
		// we need to print the maximum value of the first variable.
		double maximum = 0;
		
		for(int x = 0; x < data.elements ; x++) {
			double value = data.getUnit(x).getNumber(0);
			if(value > maximum) {
				maximum = value;
			}
		}
		
		System.out.println("The maximum value of the variable '" + data.variableNames[1] + "' is " + maximum);
		
	}
	
	Dataset getDataset() {
		try {
			//Scanner in = new Scanner(new File("src/Processing/milk.txt"));
			Scanner in = new Scanner(System.in);
			// assuming we are dealing with the standard format described in the pdf
			data = process(in);
			
		} catch (Exception e){
			/* handle it */
		}
		
		return data;
	}
	
	Dataset process(Scanner in){
		int clusters = Integer.parseInt(in.nextLine());
		int elements = Integer.parseInt(in.nextLine());
		int variables = Integer.parseInt(in.nextLine());
		String[] variableNames = in.nextLine().split("\t");
		
		data = new Dataset(clusters, elements, variables, variableNames);
		
		processLines(data, in);
		in.close();
		
		return data;
	}
	
	void processLines(Dataset data, Scanner in) {
		// elements == the amount of lines we have to add as units.
		for(int x = 0; x < data.elements ; x++) {
			Scanner tabScanner = new Scanner(in.nextLine());
			tabScanner.useDelimiter("\t");
			
			processTabs(data, tabScanner);
			tabScanner.close();
		}
	}
	
	void processTabs(Dataset data, Scanner tabScanner) {
		// we know that each line contains [variables] amount of numbers and a name
		String name = tabScanner.next();
		Unit unit = new Unit(name, data.variables);
		
		for(int y = 0;  y < data.variables ; y++) {
			unit.addNumber(tabScanner.nextDouble());
		}
		
		data.addUnit(unit);
	}

	public static void main(String[] args)	{
		new Processing().Start();
	}
}
