package dispatch.filters;

import dispatch.Event;
import dispatch.EventFilter;

public class EventTypeFilter implements EventFilter {
	private Class<? extends Event> eventType;
	
	public EventTypeFilter(Class<? extends Event> eventType) {
		this.eventType = eventType; 
	}
	
	public boolean accept(Event e) {
		return e.getClass().equals(eventType);
	}

}
