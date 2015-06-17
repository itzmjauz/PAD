package Processing;

public class ClusterRow {
	private Cluster[] clusterRow;
	private int size = 0;
	private Dataset data;
	
	ClusterRow(Dataset data) {
		this.data = data;
		this.data.normalize();
		this.data.preselect();
		
		clusterRow = new Cluster[this.data.elements];
		process(this.data);
	}
	
	private void process(Dataset data) {
		for(int x = 0 ; x < data.elements ; x++) {
			Leaf leaf = new Leaf(data.getUnit(x));
			clusterRow[size] = leaf;
			size++;
		}
	}
	
	public Cluster get(int index) {
		return clusterRow[index];
	}
	
	public void cluster(int index1, int index2) {
		Cluster cluster1 = clusterRow[index1];
		Cluster cluster2 = clusterRow[index2];
		
		Node node  = new Node(cluster1, cluster2);
		// decide to which cluster(index) we move the new node
		
		if(cluster1.size() == cluster2.size()) { // if both are the same size ( most likely 1  and 1 )
			replace(index1, node);
			remove(index2);
			
		} else if (cluster1.size() > cluster2.size()) {
			replace(index1, node);
			remove(index2);
		} else {
			replace(index2, node);
			remove(index1);
		}
	}
	
	public int size() {
		return size;
	}
	
	private void remove(int index) {
		Cluster[] cluster = new Cluster[size-1];
		
		for(int x = 0 ; x < size ; x++) {
			if(x > index) {
				cluster[x-1] = clusterRow[x];
			} else if(index == x) {
				 // do nothing;
			} else {
				cluster[x] = clusterRow[x];
			}
		}
		this.size = this.size - 1;
		this.clusterRow = cluster;
	}
	
	private void replace(int index,  Cluster cluster) {
		this.clusterRow[index] = cluster;
	}
	
	public Dataset data() {
		return data;
	}
}