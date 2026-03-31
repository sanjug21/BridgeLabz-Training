class LegacyAPI {
	@Deprecated
	void oldFeature() {
		System.out.println("This is an old feature.");
	}

	void newFeature() {
		System.out.println("This is the new feature!");
	}
}

public class DeprecatedExample {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		LegacyAPI api = new LegacyAPI();
		api.oldFeature();
		api.newFeature();
	}
}
