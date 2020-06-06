package function;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Airplane;
import entity.Airport;
import entity.Helicopter;

public class HelicopterManager {
	private Validator validator = new Validator();
	private AirportManager airportManager = new AirportManager();

	public void createHelicopter(Scanner in, ArrayList<Airplane> listAirplane) {
		System.out.print("Enter ID:");
		String iD = inputID(in, listAirplane);
		System.out.print("Enter model:");
		String model = validator.inputModel(in);
		System.out.print("Enter cruiseSpeed:");
		int cruiseSpeed = validator.inputNumber(in);
		double emptyWeight, maxTakeoffWeight;
		while (true) {
			System.out.print("Enter emptyWeight:");
			emptyWeight = validator.inputDouble(in);
			System.out.print("Enter maxTakeoffWeight:");
			maxTakeoffWeight = validator.inputDouble(in);
			if (maxTakeoffWeight > emptyWeight * 1.5) {
				System.out.println("The max takeoff weight of helicopter does not excess 1.5 times of its empty weight");
				continue;
			}
			break;
		}
		System.out.print("Enter range:");
		String range = in.nextLine();
		Helicopter helicopter = new Helicopter(iD, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, range);
		listAirplane.add(helicopter);
	}

	public void removeFromAnAirport(Scanner in, ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		ArrayList<String> listRemove = new ArrayList<String>();
		System.out.print("Enter airport's ID that you want to remove helicopter:");
		String idAirport = in.nextLine();
		if (airportManager.isExisting(idAirport, listAirport)) {
			for (Airport airport : listAirport) {
				if (airport.getID().equalsIgnoreCase(idAirport)) {
					if (airport.getHelicopterIDList().isEmpty()) {
						System.out.println(airport.getName() + " airport doesn't have any helicopter");
						return;
					}
					System.out.println("List helicopter airplane of " + airport.getName() + " airport");
					for (String idAirplane : airport.getHelicopterIDList()) {
						for (Airplane airplane : listAirplane) {
							if (idAirplane.equalsIgnoreCase(airplane.getID())) {
								System.out.println(airplane.toString());
								break;
							}
						}
					}
					while (true) {
						System.out.println(
								"Enter number of airplane you want to remove from " + airport.getName() + " airport");
						int num = validator.inputNumber(in);
						if (num <= 0 || num > airport.getHelicopterIDList().size()) {
							System.out.println("Your input is invalid");
							continue;
						}
						while (num > 0) {
							System.out.println("Enter airplane's ID:");
							String id = in.nextLine();
							if (airport.getHelicopterIDList().contains(id)) {
								if (!listRemove.contains(id)) {
									listRemove.add(id);
									num--;
								} else {
									System.out.println("You have added thid id before");
								}
							} else {
								System.out.println(airport.getName() + " airport hasn't this airplane");
							}
						}
						airport.getHelicopterIDList().removeAll(listRemove);
						break;
					}
					break;
				}
			}
		} else {
			System.out.println("This ID airport don't exist");
		}
	}

	public void add(Scanner in, ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		ArrayList<Airplane> list = listNotOwnAirport(listAirplane, listAirport);
		ArrayList<Helicopter> newlist = new ArrayList<Helicopter>();
		for (Airplane airplane : list) { // Downcasting
			newlist.add((Helicopter) airplane);
		}
		if (newlist.isEmpty()) {
			System.out.println("Don't have any helicopter that doesn't have airport ");
			return;
		}
		System.out.println("--------- List helicopters don't have airport ---------");
		for (Airplane airplane : newlist) {
			System.out.println(airplane.toString());
		}

		while (true) {
			System.out.print("Enter number of airplane that you want to add:");
			int num = validator.inputNumber(in);
			if (num > newlist.size() || num <= 0) {
				System.out.println("This number is invalid with the number of free airplaine");
				continue;
			}
			ArrayList<String> listID = new ArrayList<String>();
			while (num > 0) {
				System.out.print("What helicopter that you want to add(Enter id):");
				String id = in.nextLine();
				if (id.matches("^(RW)[0-9]{5}")) {

					if (listID.contains(id)) {
						System.out.println("You have entered this id before");
						continue;
					}

					if (isExisting(id, listAirplane)) { // existing in list all airplane
						if (isExisting(id, list)) { // existing in new list
							listID.add(id);
							num--;
						} else {
							System.out.println("This airplane is parking in airport");
						}
					} else {
						System.out.println("This airplane isn't existing");
					}
				} else {
					System.out.println("You enter wrong id, please re-enter");
				}
			}
			addToAirport(in, listID, newlist, listAirport);
			break;
		}
	}

	private void addToAirport(Scanner in, ArrayList<String> idAirplanes, ArrayList<Helicopter> listAirplane,
			ArrayList<Airport> listAirport) {
		System.out.print("Enter ID's airport that you want to add this airplane to:");
		String id = in.nextLine();
		if (airportManager.isExisting(id, listAirport)) {
			for (Airport airport : listAirport) {
				if (airport.getID().equalsIgnoreCase(id)) {
					for (String idAirplane : idAirplanes) {
						for (Helicopter airplane : listAirplane) {
							if (idAirplane.equalsIgnoreCase(airplane.getID())) {
								if (airport.getHelicopterIDList().size()<airport.getMaxRotatedwingParkingPlace()) {
									airport.getHelicopterIDList().add(idAirplane);
								}else {
									System.out.println(airport.getName() + " has fulled for helicopter");
									return;
								}
								break;
							}
						}
					}
					break;
				}
			}
		} else {
			System.out.println("This ID isn't existing");
		}

	}

	private ArrayList<Airplane> listNotOwnAirport(ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		ArrayList<Airplane> newlist = new ArrayList<Airplane>();
		for (Airplane airplane : listAirplane) { // Loc helicopter airplane
			if (airplane instanceof Helicopter) {
				newlist.add(airplane);
			}
		}
		newlist.removeAll(listOwnAirport(listAirplane, listAirport));
		return newlist;
	}

	private ArrayList<Airplane> listOwnAirport(ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		ArrayList<Airplane> newlist = new ArrayList<Airplane>();
		for (Airport airport : listAirport) {
			for (String idAirplane : airport.getHelicopterIDList()) { // Just add helicopter airplane to the list
				for (Airplane airplane : listAirplane) {
					if (airplane.getID().equalsIgnoreCase(idAirplane)) {
						newlist.add(airplane);
						break;
					}
				}
			}
		}
		return newlist;
	}

	public void displayByID(Scanner in, ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		System.out.print("Enter ID's airport:");
		String id = in.nextLine();
		if (airportManager.isExisting(id, listAirport)) {
			for (Airport airport : listAirport) {
				if (airport.getID().equalsIgnoreCase(id)) {
					for (Airplane airplane : listAirplane) {
						if (airport.getHelicopterIDList().contains(airplane.getID())) {
							System.out.println(airplane.toString());
						}
					}
				}
			}
		} else {
			System.out.println("Not find out " + id + " airport");
		}
	}

	private String inputID(Scanner in, ArrayList<Airplane> listAirplane) {
		String s;
		while (true) {
			s = in.nextLine();
			if (!s.matches("^(RW)[0-9]{5}")) {
				System.out.println("Re-enter (RW + 5 digit)");
			} else {
				if (isExisting(s, listAirplane)) {
					System.out.println("This ID has existed");
				} else {
					return s;
				}
			}
		}
	}

	private boolean isExisting(String id, ArrayList<Airplane> listAirplane) {
		for (Airplane airplane : listAirplane) {
			if (airplane.getID().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
