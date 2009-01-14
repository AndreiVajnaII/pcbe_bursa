package bursa.filters;

import bursa.OfertaCumparareEvent;
import bursa.OfertaVanzareEvent;
import dispatch.Event;
import dispatch.EventFilter;

public class CompanyFilter implements EventFilter {
	private String companie;
	
	public CompanyFilter(String companie) {
		this.companie = companie;
	}
	
	public boolean accept(Event e) {
		String companie;
		if (e instanceof OfertaVanzareEvent)
			companie = ((OfertaVanzareEvent)e).getOferta().getNumeCompanie();
		else if (e instanceof OfertaCumparareEvent)
			companie = ((OfertaCumparareEvent)e).getOferta().getOfertaVanzare().getNumeCompanie();
		else return false;
		return this.companie == companie;		
	}
}
