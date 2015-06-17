package Processing;

public class SingleLinkage implements ClusterMethod {
	DistanceMeasure method;
	
	SingleLinkage (DistanceMeasure distanceMeasure) {
		method = distanceMeasure;
	}
	
	public double calculateDistance(Cluster cluster1, Cluster cluster2) {
		
		double minimum = 10000000000.0; // a value bigger than any result we'd ever get
		
		for(int x = 0 ; x < cluster1.size() ; x++) {
			for(int y = 0 ; y < cluster2.size() ; y++) {
				double distance = method.calculateDistance(cluster1.getUnits().get(x), cluster2.getUnits().get(y));
				if(Math.abs(distance) < minimum) {
					minimum = Math.abs(distance);
				}
			}
		}
		
		return minimum;
	}
}
