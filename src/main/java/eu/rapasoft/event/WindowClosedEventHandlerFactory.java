package eu.rapasoft.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Singleton
public class WindowClosedEventHandlerFactory {

    private static Stage stage;

    public static void stageLoaded(@Observes Stage stage) {
        WindowClosedEventHandlerFactory.stage = stage;
    }

    public static EventHandler<WindowEvent> windowClosedEventHandler() {
        return new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                stage.close();
            }
        };
    }

    public static EventHandler<ActionEvent> actionClosedEventHandler() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        };
    }

}
