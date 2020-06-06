package main;

import java.util.ArrayList;
import java.util.Scanner;
import entity.Airport;
import entity.Airplane;
import function.CaseFunction;

public class Main {
	public static void main(String[] args) {
		CaseFunction casefunction = new CaseFunction();
		Scanner in = new Scanner(System.in);
		ArrayList<Airport> listAirport = new ArrayList<Airport>();
		ArrayList<Airplane> listAirplane = new ArrayList<Airplane>();
		while (true) {
			System.out.println("===================== Main Menu =======================");
			System.out.println("1. Input data");
			System.out.println("2. Airport management");
			System.out.println("3. Fixed wing management");
			System.out.println("4. Helicopter management");
			System.out.println("Another number to exist");
			int key = Integer.parseInt(in.nextLine());
			switch (key) {
			case 1:
				casefunction.case1(in, listAirport, listAirplane);
				break;
			case 2:
				casefunction.case2(in, listAirport);
				break;
			case 3:
				casefunction.case3(in, listAirplane, listAirport);
				break;
			case 4:
				casefunction.case4(in, listAirplane, listAirport);
				break;
			default:
				in.close();
				return;
			}
		}
	}
}
