package blockchain.entrepreneur.cuisine.model;

public enum NutriScore {
	A,B,C,D,E;
	
	public int getValue() {
		switch (this) {
		case A :
			return 1;
		case B :
			return 2;
		case C :
			return 3;
		case D :
			return 4;
		case E :
			return 5;
		}
		return -1;
	}
}
