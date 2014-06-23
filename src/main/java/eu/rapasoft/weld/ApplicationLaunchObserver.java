package eu.rapasoft.weld;

import eu.rapasoft.event.WindowClosedEventHandlerFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class ApplicationLaunchObserver {

    @Inject
    WeldFXMLLoader weldFXMLLoader;

    public void init(@Observes Stage stage) {
        Pane rootGridPane = weldFXMLLoader.load("main.fxml");

        stage.setScene(new Scene(rootGridPane));
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setOnCloseRequest(WindowClosedEventHandlerFactory.windowClosedEventHandler());

        stage.show();

    }

}
