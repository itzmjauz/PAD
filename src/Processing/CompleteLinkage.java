package Processing;

public class CompleteLinkage implements ClusterMethod {
	DistanceMeasure method;
	
	CompleteLinkage (DistanceMeasure distanceMeasure) {
		method = distanceMeasure;
	}
	
	public double calculateDistance(Cluster cluster1, Cluster cluster2) {
		
		double maximum = 0.0;
		
		for(int x = 0 ; x < cluster1.size() ; x++) {
			for(int y = 0 ; y < cluster2.size() ; y++) {
				double distance = method.calculateDistance(cluster1.getUnits().get(x), cluster2.getUnits().get(y));
				if(Math.abs(distance) > maximum) {
					maximum = Math.abs(distance);
				}
			}
		}
		
		return maximum;
	}
}
