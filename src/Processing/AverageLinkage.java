package Processing;

public class AverageLinkage implements ClusterMethod {
	DistanceMeasure method;
	
	AverageLinkage (DistanceMeasure distanceMeasure) {
		method = distanceMeasure;
	}
	
	public double calculateDistance(Cluster cluster1, Cluster cluster2) {
		
		double sum = 0.0;
		
		for(int x = 0 ; x < cluster1.size() ; x++) {
			for(int y = 0 ; y < cluster2.size() ; y++) {
				double distance = method.calculateDistance(cluster1.getUnits().get(x), cluster2.getUnits().get(y));
				sum += Math.abs(distance);
			}
		}
		double divisor = cluster1.size() + cluster2.size();
		double average = sum / divisor;
		
		return average;
	}
}
