package Processing;

import ui.Event;
import ui.UserInterfaceFactory;
import ui.DrawUserInterface;
import ui.Colour;

public class Cartesian implements View {
	DrawUserInterface screen;
	int WIDTH = 700;
	int HEIGHT = 700;
	int RATIO = 500;
	int OFFSET = 50;
	
	Cartesian() {
		screen = UserInterfaceFactory.getDrawUI(WIDTH, HEIGHT);
	}
	
	public void draw(ClusterRow cluster) {
		// every coordinate is from 0-1 so, we can just scale it with RATIO.
		screen.clear();
		drawInterface(cluster.data());
		Colour colour;
		for(int x = 0 ; x < cluster.size() ; x++) { // draw each cluster one by one
			// every cluster should have its own colour so we do that here

			System.out.println(x);
			double[] numbers = cluster.get(x).getUnits().get(0).getRow().numberRow;
			colour = generateColour(numbers);
			
			enCircle(cluster.get(x), colour);
			
			int size = cluster.get(x).getUnits().size();
			for(int y = 0 ; y < size  ; y++) {
				
				int xCoordinate = (int) (cluster.get(x).getUnits().get(y).getNumber(0) * RATIO);
				int yCoordinate = (int) (cluster.get(x).getUnits().get(y).getNumber(1) * RATIO);
				
				screen.drawCircle(xCoordinate + OFFSET, yCoordinate + OFFSET, 10, 10, colour, true);
				String name = cluster.get(x).getUnits().get(y).name;
				screen.setCircleHotspot(xCoordinate + OFFSET, yCoordinate + OFFSET, 10, 10, name);
			}
		}
		
		screen.showChanges();
		screen.enableEventProcessing(true);
		processEvents();
	}
	
	private void enCircle(Cluster cluster, Colour colour) {
		if(cluster.size() > 1) { // we can't encircle nothing.
			// compute the average x and y from all coords, thats the center of the circle
			// compute the longest distance to any of the units within the cluster
			// for the diameter
			double xSum = 0.0;
			double ySum = 0.0;
			
			for(int x = 0 ; x < cluster.size() ; x++) {
				xSum += (cluster.getUnits().get(x).getNumber(0) * RATIO);
				ySum += (cluster.getUnits().get(x).getNumber(1) * RATIO);
			}
			double xCoordinate, yCoordinate;
			xCoordinate = (xSum / cluster.size());
			yCoordinate = (ySum / cluster.size());
			
			int diameter = getDiameter(cluster, xCoordinate, yCoordinate);
			
			screen.drawCircle((int) xCoordinate + OFFSET, (int) yCoordinate + OFFSET, diameter, diameter, colour, false);
			screen.showChanges();
		}
	}
	
	private int getDiameter(Cluster cluster, double xCoordinate, double yCoordinate) {
		UnitRow units = cluster.getUnits();
		double unitXCoordinate, unitYCoordinate, sqrt, distance, xDifference, yDifference; 
		double maxDistance = 0;
		
		for(int x = 0 ; x < units.size() ; x++) {
			unitXCoordinate = (units.get(x).getNumber(0) * RATIO);
			unitYCoordinate = (units.get(x).getNumber(1) * RATIO);
			xDifference = Math.abs(xCoordinate - unitXCoordinate);
			yDifference = Math.abs(yCoordinate - unitYCoordinate);
			
			sqrt = (xDifference * xDifference) + (yDifference * yDifference); // pythagoras
			distance = Math.sqrt(sqrt);
			
			if(distance > maxDistance) maxDistance = distance;
		}
		
		return (int) (maxDistance * 2);
		
	}
	
	private Colour generateColour(double[] seeds) { 
		// we cant make it random, so a formula on the unique data instead
		int red = 	(int) (seeds[0] * 255);
		int blue = 	(int) (seeds[1] * 255);
		int green = (int) (seeds[1] * 255 * seeds[0]);
		
		Colour colour = new Colour(red, green, blue);
		return colour;
	}
	
	private void drawInterface(Dataset data) {
		Colour colour = new Colour(0,0,0); // black ? 
		screen.drawLine(15, 15, WIDTH, 15, colour);
		screen.drawLine(15, 15, 15, HEIGHT, colour);
		screen.drawText(15, (HEIGHT - 30), data.variableNames[0], colour);
		screen.drawText((WIDTH - 150), 15, data.variableNames[1], colour);
		screen.showChanges();
	}
	
	public void processEvents() {
		boolean spacePressed = false;
		Event event;
		while(!spacePressed) {
			event = screen.getEvent();
			if(event.data.equals("Space")) {
				spacePressed = true;
			} else if(event.name.equals("mouseover")) {
				setStatus(event.data);
			}
			else {
				System.out.println(event.name + " : " +event.data);
			}
		}
	}
	
	private void setStatus(String status) {
		screen.clearStatusBar();
		screen.printf("mimi" + status);
		screen.showChanges();
	}
}
