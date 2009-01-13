package bursa;

public class BursaSimulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bursa bursa = new Bursa();
		new Cumparator("Andrei", bursa);
		new Vanzator("MICROSOFT", bursa);
	}

}
