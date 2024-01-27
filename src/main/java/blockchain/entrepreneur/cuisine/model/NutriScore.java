package blockchain.entrepreneur.cuisine.model;

public enum NutriScore {
	A, B, C, D, E;

	public int getValue() {
		switch (this) {
			case A:
				return 1;
			case B:
				return 2;
			case C:
				return 3;
			case D:
				return 4;
			case E:
				return 5;
		}
		return -1;
	}


	public static NutriScore fromValue(String value) {
		switch (value) {
			case "A":
				return NutriScore.A;
			case "B":
				return NutriScore.B;
			case "C":
				return NutriScore.C;
			case "D":
				return NutriScore.D;
			case "E":
				return NutriScore.E;
		}
		return NutriScore.E;
	}
}