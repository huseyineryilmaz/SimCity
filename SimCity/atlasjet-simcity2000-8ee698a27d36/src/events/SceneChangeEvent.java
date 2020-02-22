package events;

import javafx.event.Event;
import javafx.event.EventType;

public class SceneChangeEvent extends Event
{
    public static final EventType<SceneChangeEvent> MAIN_MENU = new EventType<>(Event.ANY , "MAIN_MENU");
    public static final EventType<SceneChangeEvent> GAME_SCENE = new EventType<>(Event.ANY , "GAME_SCENE");
    public SceneChangeEvent(EventType<? extends SceneChangeEvent> eventType)
    {
        super(eventType);
    }
}
