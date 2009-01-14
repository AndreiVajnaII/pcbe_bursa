package bursa.filters;

import bursa.OfertaCumparareEvent;
import bursa.OfertaVanzareEvent;
import dispatch.Event;
import dispatch.EventFilter;

public class PriceIntervalFilter implements EventFilter {
	private int low, high;
	
	public PriceIntervalFilter(int low, int high) {
		this.low = low;
		this.high = high;
	}
	
	public boolean accept(Event e) {
		int pret; 
		if (e instanceof OfertaVanzareEvent)
			pret = ((OfertaVanzareEvent)e).getOferta().getPret();
		else if (e instanceof OfertaCumparareEvent)
			pret = ((OfertaCumparareEvent)e).getOferta().getPret();
		else return false;
		return pret > low && pret < high;
	}

}
