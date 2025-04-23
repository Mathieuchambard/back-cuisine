package blockchain.entrepreneur.cuisine.model;

public enum UnitServes {
	SERVES, UNIT;

	public static UnitServes fromValue(String value) {
		switch (value) {
			case "SERVES":
				return UnitServes.SERVES;
			case "UNIT":
				return UnitServes.UNIT;
		}
		return UnitServes.UNIT;
	}
}
