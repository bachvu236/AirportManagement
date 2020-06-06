package entity;

public abstract class Airplane {
	private String ID;
	private String model;
	private int cruiseSpeed;
	private double emptyWeight;
	private double maxTakeoffWeight;

	public Airplane() {
	}

	public Airplane(String iD, String model, int cruiseSpeed, double emptyWeight, double maxTakeoffWeight) {
		ID = iD;
		this.model = model;
		this.cruiseSpeed = cruiseSpeed;
		this.emptyWeight = emptyWeight;
		this.maxTakeoffWeight = maxTakeoffWeight;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCruiseSpeed() {
		return cruiseSpeed;
	}

	public void setCruiseSpeed(int cruiseSpeed) {
		this.cruiseSpeed = cruiseSpeed;
	}

	public double getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(double emptyWeight) {
		this.emptyWeight = emptyWeight;
	}

	public double getMaxTakeoffWeight() {
		return maxTakeoffWeight;
	}

	public void setMaxTakeoffWeight(double maxTakeoffWeight) {
		this.maxTakeoffWeight = maxTakeoffWeight;
	}

	@Override
	public String toString() {
		return "Airplane [ID=" + ID + ", model=" + model + ", cruiseSpeed=" + cruiseSpeed + ", emptyWeight="
				+ emptyWeight + ", maxTakeoffWeight=" + maxTakeoffWeight + ", ";
	}

}
