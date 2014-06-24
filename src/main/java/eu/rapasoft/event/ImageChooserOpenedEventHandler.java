package eu.rapasoft.event;

import eu.rapasoft.controller.FileChooserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.inject.Inject;

public class ImageChooserOpenedEventHandler implements EventHandler<ActionEvent> {

    @Inject
    private FileChooserController fileChooserController;

    @Override
    public void handle(final ActionEvent e) {
        fileChooserController.handleFileSelection();
    }

}
