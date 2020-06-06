package entity;

public class Helicopter extends Airplane {
	private String range;

	public Helicopter() {
		super();
	}

	public Helicopter(String iD, String model, int cruiseSpeed, double emptyWeight, double maxTakeoffWeight,
			String range) {
		super(iD, model, cruiseSpeed, emptyWeight, maxTakeoffWeight);
		this.range = range;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return super.toString() + "range=" + range + "]";
	}

}
