package function;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Airplane;
import entity.Airport;
import entity.Fixedwing;

public class FixedwingManager {
	private Validator validator = new Validator();
	private AirportManager airportManager = new AirportManager();

	public void createFixedwing(Scanner in, ArrayList<Airplane> listAirplane) {
		System.out.print("Enter ID:");
		String iD = inputID(in, listAirplane);
		System.out.print("Enter model:");
		String model = validator.inputModel(in);
		System.out.print("Enter cruiseSpeed:");
		int cruiseSpeed = validator.inputNumber(in);
		System.out.print("Enter emptyWeight:");
		double emptyWeight = validator.inputDouble(in);
		System.out.print("Enter maxTakeoffWeight:");
		double maxTakeoffWeight = validator.inputDouble(in);
		System.out.print("Enter planeType CAG (Cargo), LGR (Long range) and PRV (Private):");
		String planeType = inputPlaneType(in);
		System.out.print("Enter minRunwaySize:");
		int minRunwaySize = validator.inputNumber(in);
		Fixedwing fixedwing = new Fixedwing(iD, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, planeType,
				minRunwaySize);
		listAirplane.add(fixedwing);
	}

	public void removeFromAnAirport(Scanner in, ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		ArrayList<String> listRemove = new ArrayList<String>();
		System.out.print("Enter airport's ID that you want to remove fixedwing:");
		String idAirport = in.nextLine();
		if (airportManager.isExisting(idAirport, listAirport)) {
			for (Airport airport : listAirport) {
				if (airport.getID().equalsIgnoreCase(idAirport)) {
					if (airport.getFixedwingIDList().isEmpty()) {
						System.out.println(airport.getName() + " airport doesn't have any fixed wing");
						return;
					}
					System.out.println("List Fixed wing airplane of " + airport.getName() + " airport");
					for (String idAirplane : airport.getFixedwingIDList()) {
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
						if (num <= 0 || num > airport.getFixedwingIDList().size()) {
							System.out.println("Your input is invalid");
							continue;
						}
						while (num > 0) {
							System.out.println("Enter airplane's ID:");
							String id = in.nextLine();
							if (airport.getFixedwingIDList().contains(id)) {
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
						airport.getFixedwingIDList().removeAll(listRemove);
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
		ArrayList<Fixedwing> newlist = new ArrayList<Fixedwing>();
		for (Airplane airplane : list) { // Downcasting
			newlist.add((Fixedwing) airplane);
		}
		if (newlist.isEmpty()) {
			System.out.println("Don't have any fixedwing that doesn't have airport ");
			return;
		}
		System.out.println("--------- List fixedwings don't have airport ---------");
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
				System.out.print("What fixedwing airplane that you want to add(Enter id):");
				String id = in.nextLine();
				if (id.matches("^(FW)[0-9]{5}")) {

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

	private void addToAirport(Scanner in, ArrayList<String> idAirplanes, ArrayList<Fixedwing> listAirplane,
			ArrayList<Airport> listAirport) {
		System.out.print("Enter ID's airport that you want to add this airplane to:");
		String id = in.nextLine();
		if (airportManager.isExisting(id, listAirport)) {
			for (Airport airport : listAirport) {
				if (airport.getID().equalsIgnoreCase(id)) {
					for (String idAirplane : idAirplanes) {
						for (Fixedwing airplane : listAirplane) {
							if (idAirplane.equalsIgnoreCase(airplane.getID())) {
								if (airport.getFixedwingIDList().size() < airport.getMaxFixedwingParkingPlace()) {
									if (airplane.getMinRunwaySize() <= airport.getRunwaySize()) {
										airport.getFixedwingIDList().add(idAirplane);
									} else {
										System.out.println(
												"Airplane ID " + airplane.getID() + " doesn't fit with this airport");
									}
								} else {
									System.out.println(airport.getName() + " has fulled for fixed wing airplane");
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
		for (Airplane airplane : listAirplane) { // Loc fixed wing airplane
			if (airplane instanceof Fixedwing) {
				newlist.add(airplane);
			}
		}
		newlist.removeAll(listOwnAirport(listAirplane, listAirport));
		return newlist;
	}

	private ArrayList<Airplane> listOwnAirport(ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		ArrayList<Airplane> newlist = new ArrayList<Airplane>();
		for (Airport airport : listAirport) {
			for (String idAirplane : airport.getFixedwingIDList()) { // Just add fixed wing airplane to the list
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
						if (airport.getFixedwingIDList().contains(airplane.getID())) {
							System.out.println(airplane.toString());
						}
					}
				}
			}
		} else {
			System.out.println("Not find out " + id + " airport");
		}
	}

	private String inputPlaneType(Scanner in) {
		String s;
		while (true) {
			s = in.nextLine();
			if (!s.matches("(CAG|LGR|PRV)")) {
				System.out.println("Re-enter, CAG (Cargo), LGR (Long range) and PRV (Private)");
				continue;
			}
			return s;
		}
	}

	private String inputID(Scanner in, ArrayList<Airplane> listAirplane) {
		String s;
		while (true) {
			s = in.nextLine();
			if (!s.matches("^(FW)[0-9]{5}")) {
				System.out.println("Re-enter (FW + 5 digit)");
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

	public void changePlaneType(Scanner in, ArrayList<Airplane> listAirplane) {
		System.out.println("--- List airplane that you can change plane type ---");
		showListAirplane(listAirplane);
		System.out.print("Enter airplane's ID that you want to change plane type:");
		String id = in.nextLine();
		if (isExisting(id, listAirplane)) {
			for (Airplane airplane : listAirplane) {
				if (airplane.getID().equalsIgnoreCase(id) && airplane instanceof Fixedwing) {
					System.out.println("Enter plane type:");
					String planeType = inputPlaneType(in);
					Fixedwing fw = (Fixedwing) airplane;
					fw.setPlaneType(planeType);
					return;
				}
			}
			System.out.println("This isn't fixed wing airplane");
		} else {
			System.out.println("Don't have this airplane");
		}
	}

	public void changeMinRunwaySize(Scanner in, ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		System.out.println("--- List airplane that you can change min runway size ---");
		showListAirplane(listNotOwnAirport(listAirplane, listAirport));
		System.out.println("NOTE: You just can change the airplanes that aren't parking");
		System.out.print("Enter airplane's ID that you want to change min runway size:");
		String id = in.nextLine();
		if (isExisting(id, listAirplane)) {
			for (Airplane airplane : listNotOwnAirport(listAirplane, listAirport)) {
				if (airplane.getID().equalsIgnoreCase(id)) {
					System.out.print("Enter min runway size:");
					int minRunwaySize = validator.inputNumber(in);
					Fixedwing fw = (Fixedwing) airplane;
					fw.setMinRunwaySize(minRunwaySize);
					return;
				}
			}
			System.out.println("This airplane is parking in airport, you can't change min runway size for it");
		} else {
			System.out.println("Don't have this airplane");
		}
	}

	private void showListAirplane(ArrayList<Airplane> listAirplane) {
		for (Airplane airplane : listAirplane) {
			if (airplane instanceof Fixedwing) {
				System.out.println(airplane.toString());
			}
		}
	}
}
