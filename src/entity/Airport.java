package entity;

import java.util.ArrayList;

public class Airport {
	private String ID;
	private String name;
	private int runwaySize;
	private int maxFixedwingParkingPlace;
	private ArrayList<String> fixedwingIDList;
	private int maxRotatedwingParkingPlace;
	private ArrayList<String> helicopterIDList;

	public Airport() {
	}

	public Airport(String iD, String name, int runwaySize, int maxFixedwingParkingPlace,
			ArrayList<String> fixedwingIDList, int maxRotatedwingParkingPlace, ArrayList<String> helicopterIDList) {
		ID = iD;
		this.name = name;
		this.runwaySize = runwaySize;
		this.maxFixedwingParkingPlace = maxFixedwingParkingPlace;
		this.fixedwingIDList = fixedwingIDList;
		this.maxRotatedwingParkingPlace = maxRotatedwingParkingPlace;
		this.helicopterIDList = helicopterIDList;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRunwaySize() {
		return runwaySize;
	}

	public void setRunwaySize(int runwaySize) {
		this.runwaySize = runwaySize;
	}

	public int getMaxFixedwingParkingPlace() {
		return maxFixedwingParkingPlace;
	}

	public void setMaxFixedwingParkingPlace(int maxFixedwingParkingPlace) {
		this.maxFixedwingParkingPlace = maxFixedwingParkingPlace;
	}

	public ArrayList<String> getFixedwingIDList() {
		return fixedwingIDList;
	}

	public void setFixedwingIDList(ArrayList<String> fixedwingIDList) {
		this.fixedwingIDList = fixedwingIDList;
	}

	public int getMaxRotatedwingParkingPlace() {
		return maxRotatedwingParkingPlace;
	}

	public void setMaxRotatedwingParkingPlace(int maxRotatedwingParkingPlace) {
		this.maxRotatedwingParkingPlace = maxRotatedwingParkingPlace;
	}

	public ArrayList<String> getHelicopterIDList() {
		return helicopterIDList;
	}

	public void setHelicopterIDList(ArrayList<String> helicopterIDList) {
		this.helicopterIDList = helicopterIDList;
	}

	@Override
	public String toString() {
		return "Airport [ID=" + ID + ", name=" + name + ", runwaySize=" + runwaySize + ", maxFixedwingParkingPlace="
				+ maxFixedwingParkingPlace + ", maxRotatedwingParkingPlace=" + maxRotatedwingParkingPlace + "]";
	}

}
