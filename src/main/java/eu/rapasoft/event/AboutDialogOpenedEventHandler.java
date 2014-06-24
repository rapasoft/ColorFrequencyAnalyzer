package eu.rapasoft.event;

import eu.rapasoft.service.DialogService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javax.inject.Inject;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
public class AboutDialogOpenedEventHandler implements EventHandler<ActionEvent> {

    @Inject
    private DialogService dialogService;

    @Override
    public void handle(ActionEvent event) {
        dialogService.showAboutDialog();
    }
}
