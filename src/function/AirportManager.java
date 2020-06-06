package function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import entity.Airport;

public class AirportManager {
	private Validator validator = new Validator();

	public void createAirport(Scanner in, ArrayList<Airport> listAirport) {
		System.out.print("Enter ID:");
		String ID = inputID(in, listAirport);
		System.out.print("Enter name's Airport:");
		String name = in.nextLine();
		System.out.print("Enter runwaySize:");
		int runwaySize = validator.inputNumber(in);
		System.out.print("Enter max Fixedwing Parking Place:");
		int maxFixedwingParkingPlace = validator.inputNumber(in);
		System.out.print("Enter max Rotatedwing Parking Place:");
		int maxRotatedwingParkingPlace = validator.inputNumber(in);
		Airport airport = new Airport(ID, name, runwaySize, maxFixedwingParkingPlace, new ArrayList<String>(),
				maxRotatedwingParkingPlace, new ArrayList<String>());
		listAirport.add(airport);
	}

	public void deleteAirport(Scanner in, ArrayList<Airport> listAirport) {
		System.out.print("Enter airport's ID you want to delete:");
		String id = in.nextLine();
		if (!isExisting(id, listAirport)) {
			System.out.println("Don't exist " + id + " airport");
			return;
		}
		for (int i = 0; i < listAirport.size(); i++) {
			if (listAirport.get(i).getID().equalsIgnoreCase(id)) {
				listAirport.remove(i);
				System.out.println("Delete airport success");
				break;
			}
		}
	}

	public void search(ArrayList<Airport> listAirport) {
		ArrayList<Airport> list2 = new ArrayList<Airport>(listAirport);
		Collections.sort(list2, new Comparator<Airport>() {

			@Override
			public int compare(Airport o1, Airport o2) {
				return o1.getID().toLowerCase().compareTo(o2.getID().toLowerCase());
			}
		});
		for (Airport airport : list2) {
			System.out.println(airport.toString());
		}
	}

	public void search(Scanner in, ArrayList<Airport> listAirport) {
		System.out.print("Enter airport's ID you want to search:");
		String id = in.nextLine();
		if (!isExisting(id, listAirport)) {
			System.out.println("Don't exist " + id + " airport");
			return;
		}
		for (Airport airport : listAirport) {
			if (airport.getID().equalsIgnoreCase(id)) {
				System.out.println(airport.toString());
				break;
			}
		}
	}

	private String inputID(Scanner in, ArrayList<Airport> listAirport) {
		String s;
		while (true) {
			s = in.nextLine();
			if (!s.matches("^(AP)[0-9]{5}")) {
				System.out.println("Re-enter (AP + 5 digit)");
				continue;
			} else {
				if (isExisting(s, listAirport)) {
					System.out.println("This ID has existed");
				} else {
					return s;
				}
			}
		}
	}

	protected boolean isExisting(String id, ArrayList<Airport> listAirport) {
		for (Airport airport : listAirport) {
			if (airport.getID().equalsIgnoreCase(id)) {
				return true;
			}
		}
		return false;
	}
}
