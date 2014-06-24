package eu.rapasoft.service;

import eu.rapasoft.event.WindowClosedEventHandlerFactory;
import eu.rapasoft.util.ApplicationProperties;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class DialogService {

    @Inject
    private ApplicationProperties applicationProperties;

    public void showErrorDialog(Exception ex) {
        openDialog("An error occured: " + ex.getMessage());
    }

    public void showAboutDialog() {
        String version = applicationProperties.getApplicationVersion();
        String author = applicationProperties.getAuthor();
        openDialog("Version " + version + ", author: " + author);
    }

    private void openDialog(String message) {
        final Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle(applicationProperties.getApplicationName());

        Button closeButton = new Button("Close");
        closeButton.setOnAction(WindowClosedEventHandlerFactory.actionClosedEventHandler(dialogStage));

        Scene dialogScene = new Scene(VBoxBuilder.create()
                .children(new Text(message), closeButton)
                .spacing(20)
                .alignment(Pos.CENTER)
                .padding(new Insets(20))
                .build());

        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

}
