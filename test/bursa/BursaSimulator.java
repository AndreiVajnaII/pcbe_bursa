package bursa;

import java.util.Random;

import bursa.filters.CompanyFilter;
import bursa.filters.PriceIntervalFilter;

import dispatch.Event;
import dispatch.Listener;
import dispatch.filters.AndFilter;
import dispatch.filters.EventTypeFilter;

public class BursaSimulator {

	/**
	 * @param args
	 */
	static Bursa bursa;
	
	public static void main(String[] args) {
		bursa = new Bursa();
		// inregistrez un logger al tranzactiilor finalizate
		bursa.inregistrareLaEveniment(new Listener() {

			public void consumeEvent(Event event) {
				TranzactieFinalizataEvent te = (TranzactieFinalizataEvent)event;
				OfertaCumparare oc = te.getOfertaCumparare();
				OfertaVanzare ov = te.getOfertaVanzare();
				System.out.println("*** Tranzactie finalizata ***");
				System.out.println("Oferta vanzare: " + ov.getNumeCompanie() + " " + ov.getNrActiuni() + " " + ov.getPret());
				System.out.println("Oferta cumparare: " + oc.getCompanie() + " " + oc.getPret());
				System.out.println("*****************************");
			}
			
		}, 
		new EventTypeFilter(TranzactieFinalizataEvent.class));
		Cumparator1();
		Vanzator1();
	}
	
	private static void lanseazaOferta(final String numeCompanie,
			int nrActiuni, int pret) {
		System.out.println(numeCompanie + " a lansat o oferta: " + 
				nrActiuni + " actiuni x " + pret + " lei");
		bursa.creeazaOfertaVanzare(numeCompanie, nrActiuni, pret);
	}
	
	// creeaza cate o oferta din 10 in 10 secunde
	// raspunde la cereri de cumparare, creand o noua oferta cu acel pret
	public static void Vanzator1() {
		final String numeCompanie = "Vanzator1";
		final Random r = new Random();
		
		AndFilter f = new AndFilter();
		f.AddFilter(new EventTypeFilter(OfertaCumparareEvent.class));
		f.AddFilter(new CompanyFilter(numeCompanie));		
		
		bursa.inregistrareLaEveniment(new Listener() {
			public void consumeEvent(Event event) {
				OfertaCumparare oc = ((OfertaCumparareEvent)event).getOferta();
				lanseazaOferta(numeCompanie, oc.getOfertaVanzare().getNrActiuni(), oc.getPret());
			}
		}, 
		f);
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {										
					int nrActiuni = r.nextInt(1000);
					int pret = r.nextInt(1000);					
					lanseazaOferta(numeCompanie, nrActiuni, pret);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}						
		}).run();
	}
	
	// cumpara numai de la Vanzator1 si doar daca pretul e intre 500 si 700
	// el ofera pret intre 90% si 110% din pretul cerut
	public static void Cumparator1() {
		final String nume = "Cumparator1";
		final Random r = new Random();
		AndFilter f = new AndFilter();
		f.AddFilter(new CompanyFilter("Vanzator1"));
		f.AddFilter(new EventTypeFilter(OfertaVanzareEvent.class));
		f.AddFilter(new PriceIntervalFilter(500, 700));
		bursa.inregistrareLaEveniment(
			new Listener() {
				public void consumeEvent(Event event) {
					OfertaVanzare o = ((OfertaVanzareEvent)event).getOferta();
					int pret = (int) ((0.9 + 0.2 * r.nextFloat()) * o.getPret());
					System.out.println(nume + " a lansat o oferta de cumparare pentru " +
							o.getNumeCompanie() + ": " + o.getNrActiuni() + " x " + o.getPret() + " lei cu pretul de " + pret + " lei");
					bursa.lanseazaOfertaCumparare(nume, o.getNumeCompanie(), o, pret);					
				}
			},
			f);
	}
}
