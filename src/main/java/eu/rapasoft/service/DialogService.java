package eu.rapasoft.service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class DialogService {

    public void showErrorDialog(Exception ex) {
        openDialog(ex.getMessage());
    }

    public void showAboutDialog() {
        String version = "Version 1.0";
        openDialog(version);
    }

    private void openDialog(String message) {
        final Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogStage.close();
            }
        });
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(message), closeButton).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

}
