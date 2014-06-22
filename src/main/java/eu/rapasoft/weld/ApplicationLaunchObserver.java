package eu.rapasoft.weld;

import eu.rapasoft.event.WindowClosedEventHandlerFactory;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

        ScrollPane scrollPane = new ScrollPane(rootGridPane);
        stage.setScene(new Scene(scrollPane));
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setOnCloseRequest(new WindowClosedEventHandlerFactory().windowClosedEventHandler());

        stage.show();

    }

}
