package bursa;

import dispatch.Event;
import dispatch.Listener;
import dispatch.filters.EventTypeFilter;

public class Cumparator {	
	//private Random r;
	
	public Cumparator(final String nume, final Bursa bursa) {		
		bursa.inregistrareLaEveniment(
			new Listener() {
				public void consumeEvent(Event event) {
					OfertaVanzare o = ((OfertaVanzareEvent)event).getOferta();
					System.out.println(nume + " a lansat o oferta de cumparare pentru " +
							o.getNumeCompanie() + ": " + o.getNrActiuni() + " x " + o.getPret() + " lei");
					bursa.lanseazaOfertaCumparare(nume, o.getNumeCompanie(), o, o.getPret());					
				}
			},
			new EventTypeFilter(OfertaVanzareEvent.class));
	}
}
