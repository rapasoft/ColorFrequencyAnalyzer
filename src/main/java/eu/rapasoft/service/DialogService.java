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
        final Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button closeButton = new Button("Image loading has failed...");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialogStage.close();
            }
        });
        ex.printStackTrace();
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text(ex.getMessage()), closeButton).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

}
