package Processing;

public class Node implements Cluster {
	private UnitRow cluster;
	public Cluster cluster1, cluster2;
	private int size = 0;
	private int depth = 0;
	
	Node (Cluster cluster1, Cluster cluster2) {
		this.size = cluster1.size() + cluster2.size();
		cluster = new UnitRow(this.size);
		this.cluster1 = cluster1;
		this.cluster2 = cluster2;
		
		add(cluster1.getUnits());
		add(cluster2.getUnits());
		setDepth(cluster1, cluster2);
	}
	
	private void add(UnitRow units) {
		for(int x = 0 ; x < units.size() ; x++) {
			cluster.add(units.get(x));
		}
	}
	
	public boolean hasChildren() { // in case I make the mistake of adding empty unit[] lists to the cluster, safety measure. 
		if(size==1) return false ;
		else return true;
	}
	
	public int size() {
		return size;
	}
	
	public UnitRow getUnits() {
		return cluster;
	}
	
	public double[] maximum() { 
		double[] maximums = new double[cluster.get(0).getRow().numberRow.length];
		
		for(int x = 0 ; x < size ; x++) {
			maximums[x] = rowMaximum(x);
		}
		
		return maximums;
	}
	
	private double rowMaximum(int row) {
		double maximum = 0;
		
		for(int x = 0 ; x < size ; x++) {
			double value = cluster.get(x).getRow().get(row);
			if(value > maximum) {
				maximum = value;
			}
		}
		
		return maximum;
	}
	
	private void setDepth(Cluster cluster1, Cluster cluster2) {
		this.depth = Math.max(cluster1.getDepth(), cluster2.getDepth()) + 1;
	}
	
	public int getDepth() {
		return depth;
	}
}
