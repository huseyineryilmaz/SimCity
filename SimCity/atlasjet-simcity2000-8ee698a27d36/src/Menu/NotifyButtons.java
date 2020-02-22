package Menu;

import javafx.event.Event;
import javafx.event.EventType;

public class NotifyButtons extends Event{
    
    public static final EventType<NotifyButtons> ANY = new EventType<>(Event.ANY , "Buttons");
    public NotifyButtons(EventType<? extends NotifyButtons> eventType) {
        super(eventType);
    }
    
}
