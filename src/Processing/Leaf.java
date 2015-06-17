package Processing;

public class Leaf implements Cluster {
	private UnitRow units = new UnitRow(1);
	private int size = 1;
	
	public Leaf (Unit unit) {
		units.add(unit);
	}
	
	public boolean hasChildren() {
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public UnitRow getUnits() {
		return units;
	}
	
	public double[] maximum() {
		return units.get(0).getRow().numberRow;
	}
	
	public int getDepth() {
		return 0; // defined in cluster
	}
	
	public int getWidth() {
		return 1; // defined in cluster
	}
}
