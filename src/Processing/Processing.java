package Processing;

import java.util.Scanner;
import java.io.PrintStream;
import ui.UIAuxiliaryMethods;
import ui.Event;

public class Processing {
	
	PrintStream out;
	Dataset data;
	ClusterRow state;
	Event event;
	
	Processing() {
		out = new PrintStream(System.out);
		UIAuxiliaryMethods.askUserForInput();
	}
	
	void Start() {
		DistanceMeasure distanceMeasure = askDistanceMeasure();
		ClusterMethod method = askClusterMethod(distanceMeasure);
		
		data = getDataset();
		ClusterRow cluster = new ClusterRow(data);
		Clusterer clusterer = new Clusterer(cluster, method);
		Cartesian view = new Cartesian();
		
		state = clusterer.cluster;
		
		while(state.size() >= data.clusters) {
			view.draw(state);
			state = clusterer.nextState();
		}
		while(true) {
			view.processEvents();
		}
	}
	
	ClusterMethod askClusterMethod(DistanceMeasure distanceMeasure) {
		String choice = UIAuxiliaryMethods.askUserForChoice("Choose your clustering method", "AverageLinkage", "CompleteLinkage", "SingleLinkage");

		if(choice.equals("AverageLinkage")) {
			return new AverageLinkage(distanceMeasure);
		} else if(choice.equals("CompleteLinkage")) {
			return new CompleteLinkage(distanceMeasure);
		} else {
			return new SingleLinkage(distanceMeasure);
		}
	}
	
	DistanceMeasure askDistanceMeasure() {
		String choice = UIAuxiliaryMethods.askUserForChoice("Choose your distance method", "Euclidean", "Pearson", "Manhattan");
		
		if(choice.equals("Euclidean")){
			return new Euclidean();
		} else if(choice.equals("Pearson")) {
			return new Pearson();
		} else {
			return new Manhattan();
		}
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
