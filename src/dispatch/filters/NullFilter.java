package dispatch.filters;

import dispatch.Event;
import dispatch.EventFilter;

public class NullFilter implements EventFilter {

	public boolean accept(Event e) {
		return true;
	}

}
