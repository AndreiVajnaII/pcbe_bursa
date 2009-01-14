package bursa;

import java.util.Random;

// un vanzator reprezinta practic compania
public class Vanzator {
	public Vanzator(final String numeCompanie, final Bursa bursa) {
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Random r = new Random();
					int nrActiuni = r.nextInt(1000);
					int pret = r.nextInt(1000);
					System.out.println(numeCompanie + " a lansat o oferta: " + 
							nrActiuni + " x " + pret + "lei");
					bursa.creeazaOfertaVanzare(numeCompanie, nrActiuni, pret);
				}
			}
			
		}).run();
	}
}
