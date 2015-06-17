package Processing;

public interface Cluster {
	//constants 
	//method signatures
	int size();
	boolean hasChildren();  // for knowing if the cluster is single or grouped
	UnitRow getUnits();
	double[] maximum();

	/**
	 * Returns the depth of the cluster.
	 * @return the depth of the cluster.
	 */
	int getDepth();
	
	/**
	 * Returns the width of the cluster.
	 * @return the width of the cluster.
	 * int getWidth(); // same as size isn't it ? 
	 */
	
}
