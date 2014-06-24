package eu.rapasoft.controller;

import eu.rapasoft.event.AboutDialogOpenedEventHandler;
import eu.rapasoft.event.ImageChooserOpenedEventHandler;
import eu.rapasoft.event.WindowClosedEventHandlerFactory;
import eu.rapasoft.weld.InputGrid;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@Singleton
public class MainController implements Initializable {

    @FXML
    public MenuItem aboutButton;
    @Inject
    private ImageChooserOpenedEventHandler imageChooserOpenedEventHandler;
    @Inject
    private AboutDialogOpenedEventHandler aboutDialogOpenedEventHandler;
    @FXML
    private MenuItem loadImageButton;
    @FXML
    private MenuItem closeButton;
    @FXML
    private GridPane inputGridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadImageButton.setOnAction(imageChooserOpenedEventHandler);
        closeButton.setOnAction(WindowClosedEventHandlerFactory.actionClosedEventHandler());
        aboutButton.setOnAction(aboutDialogOpenedEventHandler);
    }

    @Produces
    @InputGrid
    public GridPane getInputGrid() {
        return inputGridPane;
    }
}
