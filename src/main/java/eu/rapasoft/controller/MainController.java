package eu.rapasoft.controller;

import eu.rapasoft.component.InputGridPane;
import eu.rapasoft.component.RootGridPane;
import eu.rapasoft.event.ImageChooserEventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class MainController {

    @Inject
    InputGridPane inputGridPane;

    @Inject
    RootGridPane rootGridPane;

    @Inject
    ImageChooserEventHandler imageChooserEventHandler;

    public void init(@Observes Stage stage) {
        ScrollPane scrollPane = new ScrollPane(rootGridPane);

        stage.setMinWidth(600);
        stage.setMinHeight(800);
        stage.setScene(new Scene(scrollPane));

        rootGridPane.getChildren().addAll(inputGridPane);
        rootGridPane.setPadding(new Insets(12, 12, 12, 12));

        final Button openButton = new Button("Open a Picture...");

        openButton.setOnAction(imageChooserEventHandler);

        GridPane.setConstraints(openButton, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.add(openButton, 0, 0);

        stage.show();
    }

}
