package entity;

public class Fixedwing extends Airplane {
	private String planeType;
	private int minRunwaySize;

	public Fixedwing() {
		super();
	}

	public Fixedwing(String iD, String model, int cruiseSpeed, double emptyWeight, double maxTakeoffWeight,
			String planeType, int minRunwaySize) {
		super(iD, model, cruiseSpeed, emptyWeight, maxTakeoffWeight);
		this.planeType = planeType;
		this.minRunwaySize = minRunwaySize;
	}

	public int getMinRunwaySize() {
		return minRunwaySize;
	}

	public void setMinRunwaySize(int minRunwaySize) {
		this.minRunwaySize = minRunwaySize;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	@Override
	public String toString() {
		return super.toString() + "planeType=" + planeType + ", minRunwaySize=" + minRunwaySize + "]";
	}

}
