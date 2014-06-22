package eu.rapasoft.controller;

import eu.rapasoft.model.ColorFrequency;
import eu.rapasoft.service.DialogService;
import eu.rapasoft.service.FrequencyAnalyzerService;
import eu.rapasoft.service.ImageService;
import eu.rapasoft.util.LayoutBuilder;
import eu.rapasoft.weld.InputGrid;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * @author Pavol Rajzak, www.rapasoft.eu
 */
@ApplicationScoped
public class FileChooserController {

    private FileChooser fileChooser;

    private Stage stage;

    @Inject
    @InputGrid
    private Instance<GridPane> inputGridPaneInstance;

    @Inject
    private FrequencyAnalyzerService frequencyAnalyzerService;

    @Inject
    private ImageService imageService;

    @Inject
    private DialogService dialogService;

    public void initStage(@Observes Stage stage) {
        this.stage = stage;
        this.fileChooser = new FileChooser();
    }

    public void handleFileSelection() {
        File file = fileChooser.showOpenDialog(stage);
        GridPane inputGridPane = inputGridPaneInstance.get();

        if (file != null) {
            inputGridPane.getChildren().clear();
            try {
                openFile(file, inputGridPane);
            } catch (Exception ex) {
                dialogService.showErrorDialog(ex);
            }
        }
    }

    private void openFile(File file, GridPane inputGridPane) throws IOException {
        ImageView iv = new ImageView(imageService.loadFxImage(file));
        iv.setFitWidth(500);
        iv.setPreserveRatio(true);
        inputGridPane.add(iv, 0, 1, 5, 1);
        Set<ColorFrequency> frequencies = frequencyAnalyzerService.computeFrequencies(file);
        LayoutBuilder.appendToLayout(frequencies, inputGridPane);
    }

}
