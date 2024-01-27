package blockchain.entrepreneur.cuisine.model;

public enum Difficulty {
	EASY, NORMAL, HARD;
	
	public int getValue() {
		switch (this) {
			case EASY :
				return 1;
			case NORMAL :
				return 2;
			case HARD :
				return 3;
		}
		return 0;
	}

	public static Difficulty fromValue(String value) {
		switch (value) {
			case "EASY":
				return Difficulty.EASY;
			case "NORMAL":
				return Difficulty.NORMAL;
			case "HARD":
				return Difficulty.HARD;
		}
		return Difficulty.HARD;
	}
}
