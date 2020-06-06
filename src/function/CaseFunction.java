package function;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Airplane;
import entity.Airport;

public class CaseFunction {
	private Validator validator = new Validator();
	private AirportManager airportManager = new AirportManager();
	private FixedwingManager fixedwingManager = new FixedwingManager();
	private HelicopterManager helicopterManager = new HelicopterManager();

	public void case1(Scanner in, ArrayList<Airport> listAirport, ArrayList<Airplane> listAirplane) {
		while (true) {
			System.out.println("===================== Input Data =======================");
			System.out.println("1. Create airport");
			System.out.println("2. Create fixedwing airplane");
			System.out.println("3. Create helicopter");
			System.out.println("Another number to exist");
			int key = validator.inputNumber(in);
			switch (key) {
			case 1:
				airportManager.createAirport(in, listAirport);
				break;
			case 2:
				fixedwingManager.createFixedwing(in, listAirplane);
				break;
			case 3:
				helicopterManager.createHelicopter(in, listAirplane);
				break;
			default:
				return;
			}
		}
	}

	public void case2(Scanner in, ArrayList<Airport> listAirport) {
		while (true) {
			System.out.println("===================== Airport Management =======================");
			System.out.println("1. Delete airport");
			System.out.println("2. Display list of all airport information, sorted by airport ID");
			System.out.println("3. Display the status of one airport, selected by airport ID");
			System.out.println("Another number to exist");
			int key = validator.inputNumber(in);
			switch (key) {
			case 1:
				airportManager.deleteAirport(in, listAirport);
				break;
			case 2:
				airportManager.search(listAirport);
				break;
			case 3:
				airportManager.search(in, listAirport);
				break;
			default:
				return;
			}
		}
	}

	public void case3(Scanner in, ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		while (true) {
			System.out.println("===================== Fixed Wing Management =======================");
			System.out.println("1. Add fixed wing airplaine to airport");
			System.out.println("2. Remove fixed wing airplaine from airport");
			System.out.println("3. Change plane type");
			System.out.println("4. Change min runway size");
			System.out.println("5. Display list of all fixed wing airplanes with its parking airport ID");
			System.out.println("Another number to exist");
			int key = validator.inputNumber(in);
			switch (key) {
			case 1:
				fixedwingManager.add(in, listAirplane, listAirport);
				break;
			case 2:
				fixedwingManager.removeFromAnAirport(in, listAirplane, listAirport);
				break;
			case 3:
				fixedwingManager.changePlaneType(in, listAirplane);
				break;
			case 4:
				fixedwingManager.changeMinRunwaySize(in, listAirplane, listAirport);
				break;
			case 5:
				fixedwingManager.displayByID(in, listAirplane, listAirport);
				break;
			default:
				return;
			}
		}
	}

	public void case4(Scanner in, ArrayList<Airplane> listAirplane, ArrayList<Airport> listAirport) {
		while (true) {
			System.out.println("===================== Helicopter Management =======================");
			System.out.println("1. Add helicopter airplaine to airport");
			System.out.println("2. Remove helicopter airplaine from airport");
			System.out.println("3. Display list of all helicopter airplanes with its parking airport ID");
			System.out.println("Another number to exist");
			int key = validator.inputNumber(in);
			switch (key) {
			case 1:
				helicopterManager.add(in, listAirplane, listAirport);
				break;
			case 2:
				helicopterManager.removeFromAnAirport(in, listAirplane, listAirport);
				break;
			case 3:
				helicopterManager.displayByID(in, listAirplane, listAirport);
				break;
			default:
				return;
			}
		}
	}
}
