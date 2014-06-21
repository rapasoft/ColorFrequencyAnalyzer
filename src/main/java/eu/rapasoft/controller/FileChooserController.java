package eu.rapasoft.controller;

import eu.rapasoft.component.InputGridPane;
import eu.rapasoft.model.ColorFrequency;
import eu.rapasoft.service.DialogService;
import eu.rapasoft.service.FrequencyAnalyzerService;
import eu.rapasoft.service.ImageService;
import eu.rapasoft.util.LayoutBuilder;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class FileChooserController {

    final FileChooser fileChooser;

    private Stage stage;

    @Inject
    private InputGridPane inputGridPane;

    @Inject
    private FrequencyAnalyzerService frequencyAnalyzerService;

    @Inject
    private ImageService imageService;

    @Inject
    private DialogService dialogService;

    public FileChooserController() {
        fileChooser = new FileChooser();
    }

    public void initStage(@Observes Stage stage) {
        this.stage = stage;
    }

    public void handleFileSelection(Button openButton) {
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            inputGridPane.getChildren().clear();
            inputGridPane.add(openButton, 0, 0);
            openButton.setText("Please wait...");
            try {
                openFile(file, inputGridPane);
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            } finally {
                openButton.setText("Open a Picture...");
            }
        }
    }

    private void openFile(File file, InputGridPane inputGridPane) throws IOException {
        ImageView iv = new ImageView(imageService.loadFxImage(file));
        iv.setFitWidth(500);
        iv.setPreserveRatio(true);
        inputGridPane.add(iv, 0, 1, 5, 1);
        Set<ColorFrequency> frequencies = frequencyAnalyzerService.computeFrequencies(file);
        LayoutBuilder.appendToLayout(frequencies, inputGridPane);
    }
}
