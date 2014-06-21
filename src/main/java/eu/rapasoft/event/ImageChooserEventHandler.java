package eu.rapasoft.event;

import eu.rapasoft.controller.FileChooserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javax.inject.Inject;

public class ImageChooserEventHandler implements EventHandler<ActionEvent> {

    @Inject
    private FileChooserController fileChooserController;

    @Override
    public void handle(final ActionEvent e) {
        Button openButton = (Button) e.getSource();
        fileChooserController.handleFileSelection(openButton);
    }

}
