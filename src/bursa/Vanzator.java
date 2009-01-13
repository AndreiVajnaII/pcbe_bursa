package bursa;

import java.util.Random;

// un vanzator reprezinta practic compania
public class Vanzator {
	private Bursa bursa;
	private String numeCompanie;
	
	public Vanzator(String numeCompanie, Bursa bursa) {
		this.bursa = bursa;
		this.numeCompanie = numeCompanie;
		
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
					LanseazaOferta(r.nextInt(1000), r.nextInt(1000));
				}
			}
			
		}).run();
	}
	
	public void LanseazaOferta(int nrActiuni, int pret) {
		System.out.println(numeCompanie + " a lansat o oferta: " + 
				nrActiuni + " x " + pret + "lei");
		bursa.creeazaOfertaVanzare(numeCompanie, nrActiuni, pret);		
	}
}
