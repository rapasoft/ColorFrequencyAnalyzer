package eu.rapasoft.weld;

import eu.rapasoft.event.WindowClosedEventHandlerFactory;
import eu.rapasoft.util.ApplicationProperties;
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
    private WeldFXMLLoader weldFXMLLoader;

    @Inject
    private ApplicationProperties applicationProperties;

    public void init(@Observes Stage stage) {
        Pane rootGridPane = weldFXMLLoader.load("main.fxml");

        stage.setTitle(applicationProperties.getApplicationName());
        stage.setScene(new Scene(rootGridPane));
        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setOnCloseRequest(WindowClosedEventHandlerFactory.windowClosedEventHandler());

        stage.show();

    }

}
