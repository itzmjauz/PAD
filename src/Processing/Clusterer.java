package Processing;

public class Clusterer {
	ClusterRow cluster;
	ClusterMethod method;
	
	Clusterer(ClusterRow cluster, ClusterMethod method) {
		this.cluster = cluster;
		this.method = method;
	}
	
	public ClusterRow nextState() {
		double minimal = 10000; // some high value that we will certainly not reach
		int index1 = 0; // we need to remember which two nodes were closest
		int index2 = 0; // so .... remember the indexes ( x,y )
		
		for(int x = 0 ; x < cluster.size() ; x++) { // every unique pair of clusters pls. 
			Cluster cluster1 = cluster.get(x);
			
			for(int y = (x + 1) ; y < cluster.size() ; y++) { // only unique entry's
				if(x != y) {
					Cluster cluster2 = cluster.get(y);
					
					double result = method.calculateDistance(cluster1, cluster2);
					if(minimal > result) { 
						minimal = result;
						index1 = x;
						index2 = y;
					}
				}
			}
		}
		System.out.println(index1 + " and " + index2 + " were clustered");
		cluster.cluster(index1, index2);
		return cluster;
	}
}
