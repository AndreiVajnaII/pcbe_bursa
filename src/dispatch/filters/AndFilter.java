package dispatch.filters;

import java.util.ArrayList;

import dispatch.Event;
import dispatch.EventFilter;

// Filtru ce combina alte filtre prin AND
public class AndFilter implements EventFilter {
	ArrayList<EventFilter> filters;
	
	public AndFilter() {
		filters = new ArrayList<EventFilter>();
	}
	
	public void AddFilter(EventFilter f) {
		filters.add(f);
	}
	
	public boolean accept(Event e) {
		for (EventFilter f : filters)
			if (f.accept(e) == false)
				return false;
		return true;
	}

}
