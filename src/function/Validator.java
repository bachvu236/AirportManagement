package function;

import java.util.Scanner;

public class Validator {

	public int inputNumber(Scanner in) {
		int num;
		while (true) {
			try {
				num = Integer.parseInt(in.nextLine());
				return num;
			} catch (Exception e) {
				System.out.println("Re-enter");
			}
		}
	}

	public double inputDouble(Scanner in) {
		double num;
		while (true) {
			try {
				num = Double.parseDouble(in.nextLine());
				return num;
			} catch (Exception e) {
				System.out.println("Re-enter");
			}
		}
	}

	public String inputModel(Scanner in) {
		String s;
		while (true) {
			s = in.nextLine();
			if (s.length() > 40) {
				System.out.println("The model size is maximum 40 characters");
				continue;
			}
			return s;
		}
	}

}
